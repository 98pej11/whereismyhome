import http from "@/api/http";
const commentStore = {
    namespaced: true,
    state:{
        comments: ["hi"],
    },

    mutations:{
        SET_COMMENTS: (state, comments)=>{
            state.comments = comments;
        },

        CLEAR_COMMENT: (state) => {
            state.comment = [];
        }
    },


    actions:{
        async getComments({commit}, articleno){
            http.get(`/memo/${articleno}`)
            .then(({data})=>{
                commit("SET_COMMENTS",data);
            }).
            catch((error)=>{
                console.log(error);
            })
        },

        async writeComment({commit}, params){
            http.post(`/memo/`,params)
            .then(({data})=>{
                commit("SET_COMMENTS",data);
            }).
            catch((error)=>{
                console.log(error);
            })
        },

        async deleteComment({commit},c){
            http.delete(`/memo/${c.articleNo}/${c.memono}`)
            .then(({data})=>{
                commit("SET_COMMENTS",data);
            })
            .catch((error)=>{
                console.log(error);
            })
        },

        async updateComment({commit}, params){
            http.put(`/memo/`,params)
            .then(({data})=>{
                commit("SET_COMMENTS",data);
            })
            .catch((error)=>{
                console.log(error);
            })
        },

        async deleteAllComment({commit},articleno){
            http.delete(`/memo/${c.articleno}`)
            .then(()=>{
                commit("SET_COMMENTS",[]);
            })
            .catch((error)=>{
                console.log(error);
            })
        },
    }
}

export default commentStore;