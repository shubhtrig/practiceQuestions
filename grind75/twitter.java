// https://leetcode.com/problems/design-twitter
class Twitter {
    // Map<Integer, Set<Integer>> followers;
    Map<Integer, Set<Integer>> following;
    Map<Integer, ArrayList<Tweet>> userTweets;
    int tweetCount;

    public Twitter() {
        // followers = new HashMap<>();
        following = new HashMap<>();
        userTweets = new HashMap<>();
        tweetCount = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        if (!userTweets.containsKey(userId))
            userTweets.put(userId, new ArrayList<>());
        
        userTweets.get(userId).add(new Tweet(tweetId, tweetCount++));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        ArrayList<Tweet> list = userTweets.getOrDefault(userId, new ArrayList<Tweet>());
        List<Integer> ans = new ArrayList<>();

        PriorityQueue<Tweet> pq = new PriorityQueue<>(10, (a,b) -> b.timestamp - a.timestamp);
        if (userTweets.containsKey(userId)) {
            addTweetsToPq(pq, userTweets.get(userId));
        }

        Set<Integer> followings = following.getOrDefault(userId, new HashSet<>());
        for (Integer i : followings) {
            if (userTweets.containsKey(i)) {
                addTweetsToPq(pq, userTweets.get(i));
            }
        }

        int i = 0;
        while (!pq.isEmpty()) {
            if (i == 10)
                break;
            ans.add(pq.poll().tweetId);
            i++;
        }
        return ans;
    }
    
    public void follow(int followerId, int followeeId) {
        // if (!followers.containsKey(followeeId)) {
        //     followers.put(followeeId, new HashSet<>());
        // }

        // followers.get(followeeId).add(followerId);

        if (!following.containsKey(followerId)) {
            following.put(followerId, new HashSet<>());
        }

        following.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (!following.containsKey(followerId))
            return;
        // followers.get(followeeId).remove(followerId);
        following.get(followerId).remove(followeeId);
    }

    private void addTweetsToPq(PriorityQueue<Tweet> pq, ArrayList<Tweet> list) {
        int end = Math.max(0, list.size() - 10);
        for (int i = list.size() -1 ; i >= 0; i--) {
            pq.add(list.get(i));
        }
    }

    class Tweet {
        int tweetId;
        int timestamp;

        Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */