<template>
  <div>
    <Header/>
    <v-container>
      <v-row
        class="mb-6"
        justify="center"
        no-gutters
      >
        <v-col cols="12" class="mt-3">
          <v-carousel
            :show-arrows="false"
            v-if="selected_picture"
            style="height: 30vh;"
          >
            <v-carousel-item
              v-for="(file,i) in files"
              :key="i"
              reverse-transition="fade-transition"
              transition="fade-transition"
              tile
            >
              <v-img :src="url[i]" contain max-height="30vh" max-width="100vw"></v-img>
            </v-carousel-item>
          </v-carousel>
          <div
            class="carousel"
            :class="{ lower : selected_picture }"
          >
            <v-file-input
              :class="{ changewide : !selected_picture }"
              small-chips
              multiple
              accept="image/png, image/jpeg, image/bmp"
              label="Select picture(s)"
              prepend-icon="mdi-camera"
              @change="addFiles"
              v-model="files"
            ></v-file-input>
          </div>
          <p v-if="pic_error" style="color: red; margin-top: 2vh; text-align: center;">사진을 입력해주세요</p>
        </v-col>
        <v-col cols="12" class="my-3 px-1">
          <v-textarea
            @keyup="inputText"
            v-model="text"
            fluid
            no-resize
            hide-details
            rows="7"
            style="height: 30vh; border-radius: 15px;"
            outlined
            label="Text"
            placeholder="내용을 입력해주세요."
          ></v-textarea>
          <p v-if="text_error" style="color: red; text-align: center;">내용을 입력해주세요</p>
        </v-col>
        <v-col cols="12">
          <v-text-field
            v-model="tagtext"
            filled
            label="tags"
            placeholder="#태그 #취미"
          ></v-text-field>
        </v-col>
        
      </v-row>
    </v-container>

    <v-btn
      class="mid mt-5"
      fab
      dark
      large
      color="cyan"
      @click="checkForm"
    >
      <v-icon dark>
        mdi-pencil
      </v-icon>
    </v-btn>
  </div>
</template>

<script>
  import Header from '@/components/Header'

  export default {
    name: 'FeedCreate',
    components: {
      Header,
    },
    data() {
      return {
        text: "",
        files: [],
        url: [],
        selected_picture: false,
        pic_error: false,
        text_error: false,
        tagtext: '',
      }
    },
    methods: {
      inputText() {
        this.text_error = false
      },
      addFiles() {
        this.url.length = 0
        var maxSize = 5 * 1024 * 1024
        var maxSumSize = 10 * 1024 * 1024
        var sizebool = true
        var sizesum = 0

        this.files.forEach(file => {
          var fileSize = file.size
          sizesum += fileSize
          if (fileSize > maxSize){
            alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.")
            sizebool = false
            this.files.length = 0
          }
        })

        if (sizesum > maxSumSize) {
          alert("첨부파일 합계 사이즈는 10MB 이내로 등록 가능합니다.")
          sizebool = false
          this.files.length = 0
        }

        if (sizebool) {
          this.files.forEach(file => {
            this.url.push(URL.createObjectURL(file))
          });
  
          this.selected_picture = true
          if (this.files.length == 0) {
            this.selected_picture = false
          }
          this.pic_error = false          
        }
      },
      checkForm() {
        if (this.files.length == 0) {
          this.pic_error = true
        }
        else {
          this.pic_error = false
        }
        if (this.text.length  == 0) {
          this.text_error = true
        }
        else {
          this.text_error = false
        }

        if (!this.pic_error && !this.text_error) {
          const form = new FormData()
          const files = this.files
          
          for (let i = 0; i < files.length; i++) {
            form.append('files', files[i])
          }
          form.append('email', localStorage.getItem('email'))
          form.append('nickname', localStorage.getItem('user'))
          form.append('comment', this.text)
          console.log(this.tags)
          // for (let i = 0; i < this.tags.length; i++) {
          //   form.append('tags', this.tags[i])
          // }
          form.append('tags', this.tags)

          this.$store.dispatch('CREATE_FEED', form)
            .then(() => {
              this.$router.push('/main')
            })

        }
      }
    },
    computed: {
      tags() {
        return this.tagtext.trim().replace(/,/g, '').split('#').filter((word) => word.length).map(word => word.trim())
      }
    }
  }

</script>

<style scoped>
.mid {
  left: 50%;
  transform: translate(-50%, -50%);
}
.lower {
  height: auto !important;
  margin-top: 5vh;
  background-color: white !important;
  width: 100% !important;
}
.changewide {
  max-width: 50% !important;
}
.carousel {
  height: 30vh; display: flex; 
  align-items: center; 
  justify-content: center; 
  background-color: rgba(209, 209, 209, 0.596); 
  border-radius: 15px;
}
</style>
