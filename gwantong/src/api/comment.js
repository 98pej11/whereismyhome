import http from"./http.js";

const api = http;

async function Comments(articleno,success, fail) {
    api.get(`/memo/${articleno}`).then(success).catch((fail)=>{
        console.log(fail);
    });
}

export { Comments };