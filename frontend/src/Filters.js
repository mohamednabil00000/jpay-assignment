import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from 'axios';

export default function Filters(props) {

  const handleChangeCountry = (event) => {
    let nextPageButton = document.querySelector(
        '[aria-label="Go to next page"]'
      );
    if (props.state !== "" && event.target.value !== "") { // This means we will filter with both country and state
        axios.get(`http://localhost:8083/customers/country/${event.target.value}?page=${0}&size=${props.rowsSize}&state=${props.state}`)
          .then(res => {
            props.setRows(res.data);
            props.setCountry(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
          });
    } else if (props.state === "" && event.target.value !== "") { // This means we will only filter with country
        axios.get(`http://localhost:8083/customers/country/${event.target.value}?page=${0}&size=${props.rowsSize}`)
          .then(res => {
            props.setRows(res.data);
            props.setCountry(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
          });
    } else if (props.state !== "" && event.target.value === "") {
        axios.get(`http://localhost:8083/customers/state/${props.state}?page=${0}&size=${props.rowsSize}`)
          .then(res => {
            props.setRows(res.data);
            props.setCountry(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
          });
    } else {
        axios.get(`http://localhost:8083/customers?page=${0}&size=${props.rowsSize}`)
          .then(res => {
            props.setRows(res.data);
            props.setCountry(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
          });
    }
  };

  const handleChangeState = (event) => {
      let nextPageButton = document.querySelector(
          '[aria-label="Go to next page"]'
        );

    if (props.country !== "" && event.target.value !== "") { // This means we will filter with both country and state
        axios.get(`http://localhost:8083/customers/country/${props.country}?page=${0}&size=${props.rowsSize}&state=${event.target.value}`)
          .then(res => {
            props.setRows(res.data);
            props.setState(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
        });
    } else if (props.country === "" && event.target.value !== ""){ // This means we will only filter with state
        axios.get(`http://localhost:8083/customers/state/${event.target.value}?page=${0}&size=${props.rowsSize}`)
          .then(res => {
            props.setRows(res.data);
            props.setState(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
        });
    } else if (props.country !== "" && event.target.value === "") {
        axios.get(`http://localhost:8083/customers/country/${props.country}?page=${0}&size=${props.rowsSize}`)
          .then(res => {
            props.setRows(res.data);
            props.setState(event.target.value);
            props.setPage(0);
            nextPageButton.classList.remove("Mui-disabled");
        });
    } else {
         axios.get(`http://localhost:8083/customers?page=${0}&size=${props.rowsSize}`)
           .then(res => {
             props.setRows(res.data);
             props.setState(event.target.value);
             props.setPage(0);
             nextPageButton.classList.remove("Mui-disabled");
         });
    }
  };

  return (
    <div>
      <FormControl sx={{ m: 1, minWidth: 200 }}>
        <InputLabel id="country">Filter by Country</InputLabel>
        <Select
          labelId="country"
          id="country"
          value={props.country}
          label="Filter by Country"
          onChange={handleChangeCountry}
        >
          <MenuItem value=""><em>All</em></MenuItem>
          <MenuItem value="Cameroon">Cameroon</MenuItem>
          <MenuItem value="Ethiopia">Ethiopia</MenuItem>
          <MenuItem value="Morocco">Morocco</MenuItem>
          <MenuItem value="Mozambique">Mozambique</MenuItem>
          <MenuItem value="Uganda">Uganda</MenuItem>
        </Select>
      </FormControl>
      <FormControl sx={{ m: 1, minWidth: 200 }}>
        <InputLabel id="state">Filter by State</InputLabel>
        <Select
          labelId="state"
          id="state"
          value={props.state}
          label="Filter by State"
          onChange={handleChangeState}
        >
          <MenuItem value=""><em>All</em></MenuItem>
          <MenuItem value="valid">Valid</MenuItem>
          <MenuItem value="not valid">Invalid</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
}