FROM alpine
LABEL maintainer="314460007@qq.com"
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories
RUN apk add nodejs nodejs-npm
WORKDIR ./front/mysproject/src
RUN npm install
EXPOSE 3030
ENTRYPOINT ["node", "./app.js"]