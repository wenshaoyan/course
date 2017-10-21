<template>
  <section>
    <mu-appbar title="后台管理" :zDepth="3">
      <mu-icon value="settings_input_svideo" color="#f48fb1" slot="left">
      </mu-icon>
      <mu-chip backgroundColor="rgba(0, 0, 0, 0)" slot="right">
        <mu-avatar src="https://avatars1.githubusercontent.com/u/22613580?v=4&s=40" :size="32">
        </mu-avatar><span class="bar-name">wenshao</span>
      </mu-chip>
    </mu-appbar>
    <mu-row>
      <mu-col width="20" tablet="20" desktop="20">
        &nbsp;
      </mu-col>
      <mu-col width="20" tablet="20" desktop="20">
        &nbsp;
      </mu-col>
    </mu-row>
  </section>
</template>
<style scoped>
  .bar-name {
    color: white;
  }
  .row-left {
    border-right: 1px dashed rgba(0, 0, 0, 0.1);
  }
  .menu-active {
    border-left: 2px solid lightblue;
  }
  .board-left,
  .board-right {
    padding: 5px;
  }
  .paper-draft {
    padding: 10px;
  }
  .list-menu {
    background: #eeeeee;
  }
</style>
