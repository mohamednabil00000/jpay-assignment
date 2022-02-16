FROM node:12-alpine

COPY . .
RUN npm ci
RUN npm run build

EXPOSE 3000

CMD ["npm", "start"]
