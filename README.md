# 前后端分离文件上传
## Upload.vue
```vue
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
```
## FileUploadController
```java
@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file) {
        try {
            fileUploadService.uploadFiles(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
## FileUploadService
```java
@Service
public class FileUploadService {

    /**
     * 多文件上传
     * @param files
     */
    public void uploadFiles(MultipartFile[] files) throws IOException {
        if (files != null && files.length > 0) {
            for(int i =0 ;i< files.length; i++) {
                saveFile(files[i]);
            }
        }
    }

    /**
     * 单文件上传（transferTo）
     * @param file
     */
    public void uploadFile(MultipartFile file) {
        //判断文件是否为空
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //加个时间戳，尽量避免文件名称重复
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传后的路径
            String filePath = "D:\\Vue\\test\\upload\\src\\main\\resources\\files\\";
            //创建文件路径
            File dest = new File(filePath + fileName);
            // 检测文件父目录是否存在,不存在则递归创建目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                //保存文件
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 单文件上传（通过使用BufferedOutputStream）
     * @param file
     * @throws IOException
     */
    public void saveFile(MultipartFile file) throws IOException {
        //判断文件是否为空
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //加个时间戳，尽量避免文件名称重复
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            byte[] bytes = file.getBytes();

            // 文件上传后的路径
            String filePath = "D:\\Vue\\test\\fileupload-svc\\src\\main\\resources\\files\\";
            //创建文件路径
            File dest = new File(filePath + fileName);

            // 检测文件父目录是否存在,不存在则递归创建目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(dest));
            buffStream.write(bytes);
            buffStream.close();
        }
    }
}

```
