import * as React from 'react';
import { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { makeStyles } from '@mui/styles';
import { TablePagination } from '@mui/material';
import Filters from './Filters';
import axios from 'axios';
import CheckIcon from '@mui/icons-material/Check';
import CloseIcon from '@mui/icons-material/Close';

const useStyles = makeStyles({
  root: {
    margin: '150px',
  },
  tableHeader: {
    background: '#e1e1ef',
  },
  tableCell: {
    fontWeight: '600 !important'
  },
  check: {
    color: 'green',
  },
  close: {
    color: 'red',
  },
});

export default function App() {
  const classes = useStyles();

  const [page, setPage] = useState(0);
  const [rows, setRows] = useState([]);
  const [rowsSize, setRowsSize] = useState(10);
  const [country, setCountry] = React.useState('');
  const [state, setState] = React.useState('');

  const handleChangePage = (event, newPage) => {
    let nextPageButton = document.querySelector(
        '[aria-label="Go to next page"]'
      );
    if (country !== "" && state === "") {
        axios.get(`http://localhost:8083/customers/country/${country}?page=${newPage}&size=${rowsSize}`)
          .then(res => {
            if (res.data.length > 0) {
                setRows(res.data);
                setPage(newPage);
                nextPageButton.classList.remove("Mui-disabled");
            } else {
              nextPageButton.classList.add("Mui-disabled");
            }
          });
    } else if (country === "" && state !== "") {
        axios.get(`http://localhost:8083/customers/state/${state}?page=${newPage}&size=${rowsSize}`)
          .then(res => {
            if (res.data.length > 0) {
                setRows(res.data);
                setPage(newPage);
                nextPageButton.classList.remove("Mui-disabled");
            } else {
              nextPageButton.classList.add("Mui-disabled");
            }
          });
    } else if (country !== "" && state !== "") {
        axios.get(`http://localhost:8083/customers/country/${country}?page=${newPage}&size=${rowsSize}&state=${state}`)
          .then(res => {
            if (res.data.length > 0) {
                setRows(res.data);
                setPage(newPage);
                nextPageButton.classList.remove("Mui-disabled");
            } else {
              nextPageButton.classList.add("Mui-disabled");
            }
          });
    } else {
        axios.get(`http://localhost:8083/customers?page=${newPage}&size=${rowsSize}`)
          .then(res => {
            if (res.data.length > 0) {
                setRows(res.data);
                setPage(newPage);
                nextPageButton.classList.remove("Mui-disabled");
            } else {
              nextPageButton.classList.add("Mui-disabled");
            }
          });
    }
  };

  const handleRowsChange = (event, newRowsSize) => {
      if (country !== "" && state === "") {
          axios.get(`http://localhost:8083/customers/country/${country}?page=${page}&size=${newRowsSize.props.value}`)
            .then(res => {
                setRowsSize(newRowsSize.props.value);
                setRows(res.data);
            });
      } else if (country === "" && state !== "") {
          axios.get(`http://localhost:8083/customers/state/${state}?page=${page}&size=${newRowsSize.props.value}`)
            .then(res => {
                setRowsSize(newRowsSize.props.value);
                setRows(res.data);
            });
      } else if (country !== "" && state !== "") {
          axios.get(`http://localhost:8083/customers/country/${country}?page=${page}&size=${newRowsSize.props.value}&state=${state}`)
            .then(res => {
                setRowsSize(newRowsSize.props.value);
                setRows(res.data);
            });
      } else {
          axios.get(`http://localhost:8083/customers?page=${page}&size=${newRowsSize.props.value}`)
            .then(res => {
                setRowsSize(newRowsSize.props.value);
                setRows(res.data);
            });
      }
  };

  useEffect(() => {
    axios.get(`http://localhost:8083/customers?page=${page}&size=${rowsSize}`)
      .then(res => {
        setRows(res.data);
      });
  }, []);

  return (
    <div className={classes.root}>
        <Filters setRows={setRows} page={page} rowsSize={rowsSize} country={country} setCountry={setCountry} state={state} setState={setState} setPage={setPage}/>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead className={classes.tableHeader}>
              <TableRow >
                <TableCell className={classes.tableCell}>Name</TableCell>
                <TableCell align="right" className={classes.tableCell}>Number</TableCell>
                <TableCell align="right" className={classes.tableCell}>Country</TableCell>
                <TableCell align="right" className={classes.tableCell}>State</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {row.name}
                  </TableCell>
                  <TableCell align="right">{row.phone}</TableCell>
                  <TableCell align="right">{row.country}</TableCell>
                  <TableCell align="right">{row.state === "valid" ? <CheckIcon className={classes.check}/> : <CloseIcon className={classes.close}/>}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          component="div"
          count={-1}
          rowsPerPage={rowsSize}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleRowsChange}
        />
    </div>
  );
}
