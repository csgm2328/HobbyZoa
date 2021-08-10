<template>
  <div>
    <Header/>
    <v-container>
      <v-list
        two-line
        subheader
      >
        <!-- <v-subheader>프로필 사진 변경</v-subheader>
        <v-divider class="my-3"></v-divider> -->
        <v-subheader>프로필 메세지 변경</v-subheader>
        <ProfileCommentChange/>
        <v-divider class="my-3"></v-divider>
        <v-subheader class="my-3">이메일 인증</v-subheader>
        <div class="d-flex justify-space-around">
          {{ user.email }}
          <v-icon v-if="user.emailVerified" color="light-green accent-3">mdi-check-circle-outline</v-icon>
          <v-icon v-else color="red">mdi-alpha-x-circle-outline</v-icon>
          <MailReconfirm 
            v-if="!user.emailVerified"
            :email="user.email"
          />
        </div>
        <v-divider class="my-3"></v-divider>
        <v-subheader>닉네임 변경</v-subheader>
        <NicknameChange/>
        <v-divider class="my-3"></v-divider>
        <v-subheader>비밀번호 변경</v-subheader>
        <PasswordChange/>
      </v-list>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import PasswordChange from '@/components/PasswordChange'
import NicknameChange from '@/components/NicknameChange'
import ProfileCommentChange from '@/components/ProfileCommentChange'
import MailReconfirm from '@/components/MailReconfirm'

export default {
  name: 'UserSetting',
  components: {
    Header,
    PasswordChange,
    NicknameChange,
    ProfileCommentChange,
    MailReconfirm
  },
  created() {
    this.$store.dispatch('FETCH_USER_SETTING', localStorage.getItem('email'))
  },
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
}
</script>

<style>

</style>