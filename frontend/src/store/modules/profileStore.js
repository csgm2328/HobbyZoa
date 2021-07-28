const profileStore = {
  namespaced: true,
  state: {
    //   feeds: [],
  },
  getters: {
    // getUserFeeds(state) {
    //     return state.feeds
    // },
  },
  mutations: {
    // getFeeds(state, feeds) {
    //     state.feeds = feeds
    // },
  },
  actions: {
    // create feed
    // async GET_FEED({ commit })  {
    //   const GET_USERFEED_URL = '/feed/{}'
    //   const response = await axios.get(FEED_CREATE_URL, data, config)
    // },
    test(context, value) {
        console.log(value)
        console.log(context)
    }
  }
}

export default profileStore