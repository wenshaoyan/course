<template>
  <div class="modal is-active">
    <div class="modal-background"></div>
    <div class="modal-content">
      <div class="box">
        <slot>默认模态框内容</slot>
      </div>
    </div>
    <button class="modal-close" @click="$emit('fireclose')"></button>
  </div>
</template>
