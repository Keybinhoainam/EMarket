<script setup>
import ErrorHeader from "@/components/Error/ErrorHeader.vue";
import misc404 from "@/assets/images/404.png";
</script>

<template>
    <div class="misc-wrapper">
        <ErrorHeader
            error-code="404"
            error-title="Page Not Found ⚠️"
            error-description="We couldn't find the page you are looking for."
        />

        <!-- 👉 Image -->
        <div class="misc-avatar w-100 text-center">
            <VImg :src="misc404" alt="Coming Soon" :max-width="500" class="mx-auto" />
            <VBtn to="/" class="mt-10"> Back to Home </VBtn>
        </div>
    </div>
</template>

<style lang="scss">
.misc-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 1.25rem;
    min-block-size: calc(var(--vh, 1vh) * 100);
}

.misc-avatar {
    z-index: 1;
}
</style>
