<template>
  <div class="card h-100 w-100">
    <div class="embed-responsive embed-responsive-16by9">
      <img
        class="card-img-top embed-responsive-item"
        :src="category.image?category.image:noImageUrl"
        alt="Category Image"
      />
    </div>

    <div class="card-body">
      <!-- <router-link :to="{ name: 'ListProducts', params: { id: category.id } }"
        ><h5 class="card-title">{{ category.categoryName }}</h5></router-link
      > -->
      <h5 class="card-title">{{ category.category_name }}</h5>
      <p class="card-text font-italic">
        {{ category.description }}...
      </p>
      <router-link
        id="edit-category"
        :to="{ name: 'EditCategory', params: { id: category.id } }"
        v-show="$route.name == 'Category'"
      >
        Edit
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoryBox",
  props: ["category"],
  data() {
    return {
      noImageUrl:"https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png"
    }
  },
  methods: {
    listProducts() {
      this.$router.push({
        name: "ListProducts",
        params: { id: this.category.id },
      });
    },
  },
};
</script>

<style scoped>
.embed-responsive .card-img-top {
  object-fit: cover;
}

a {
  text-decoration: none;
}

.card-title {
  color: #484848;
  font-size: 1.1rem;
  font-weight: 400;
}

.card-title:hover {
  font-weight: bold;
}

.card-text {
  font-size: 0.9rem;
}

#edit-category {
  float: right;
}
</style>
