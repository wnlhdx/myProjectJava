<template>
  <router-view />
</template>

<script>
export default {
  name: 'app',
  mounted() {
    this.$router.push('/index')
  },
}
</script>
