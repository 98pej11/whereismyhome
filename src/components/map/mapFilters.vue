<template>
    <div>
        <div id="area">
            <house-search-bar></house-search-bar>
        </div>
    </div>
</template>

<script>
import HouseSearchBar from '../house/HouseSearchBar.vue';
export default ({
    name: "MapFilter",
    component:{
        HouseSearchBar,
    }
})
</script>
