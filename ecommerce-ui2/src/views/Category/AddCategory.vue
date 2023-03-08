<template>
    <div class="container">
        <div class="row">
            <div class="col-11 text-center">
                <h3 class="pt-3">Add Category</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-5">
                <form>
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" v-model="categoryName" />
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea type="text" class="form-control" v-model="description"/>
                </div>
                <div class="form-group">
                    <label>Image</label>
                    <input type="text" class="form-control" v-model="imageUrl"/>
                </div>
                <button type="button" class="btn btn-success" @click="addCategory">Submit</button>
            </form>
            </div>
            <div class="col-3"></div>
            
        </div>
    </div>
</template>
<script>
const axios=require("axios");
const sweetalert = require("sweetalert");

export default {
    data() {
        return{
            categoryName:"",
            description:"",
            imageUrl:"",
        };
    },
    methods:{
        addCategory(){
            console.log(this.categoryName, this.description,this.imageUrl);
            const newCategory ={
                categoryName:this.categoryName,
                description:this.description,
                imageUrl:this.imageUrl,
            };

            const baseURL = "http://localhost:8090";
            axios({
                method:'post',
                url:`${baseURL}/category/create`,
                data:JSON.stringify(newCategory),
                headers:{
                    'content-type':'application/json'
                }
            }).then(()=>{
                sweetalert({
                    text:"Category Added Successfully",
                    icon:"success",
                });
            }).catch((err)=>{
                
                console.log(err);
            });
        },
        
    },
};
</script>
<style scoped></style>