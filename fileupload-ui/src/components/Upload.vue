<template>
    <div>
        <el-form>
         <el-form-item label="附件上传" prop="fysjtDesc">
            <el-upload
                class="upload-demo"
                action="http://localhost:8081/uploadFile/"
                multiple
                :limit="3"
                :on-exceed="handleExceed"
                :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
        </el-form-item>
        </el-form>
    </div>
</template>

<script>

export default {
    data() {
      return {
        fileList: []
      };
    },
    methods: {
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      }
    }
  }
</script>