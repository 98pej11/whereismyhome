import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:9999/vue",
    headers: {
      "Content-Type": "multipart/form-data"
    },
  });
  