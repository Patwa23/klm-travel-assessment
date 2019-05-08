### STAGE 1: Build ###

# We label our stage as ‘builder’ng
FROM node:alpine as builder
LABEL author="Prakash Patwa"

COPY package.json ./

RUN npm set progress=false && npm config set depth 0 && npm cache clean --force

## Storing node modules on a separate layer will prevent unnecessary npm installs at each build
RUN npm i && mkdir /ng-app && cp -R ./node_modules ./ng-app

WORKDIR /ng-app

COPY . .

# RUN chmod +x /ng-app/node_modules/.bin/ng
## Build the angular app in production mode and store the artifacts in dist folder
RUN $(npm bin)/ng build --prod --aot



### STAGE 2: Setup ###
FROM nginx:alpine

## Copy our default nginx config
COPY default.conf /etc/nginx/conf.d/default.conf

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder
COPY --from=builder /ng-app/dist /usr/share/nginx/html
#run nginx
CMD ["nginx", "-g", "daemon off;"]

