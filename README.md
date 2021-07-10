# Project 4 - Instagram for FBU

Instagram is a photo sharing app using Parse as its backend.

Time spent: 22 hours spent in total

## User Stories

The following **required** functionality is completed:

- [*] User sees app icon in home screen.
- [*] User can sign up to create a new account using Parse authentication
- [*] User can log in to his or her account
- [*] The current signed in user is persisted across app restarts
- [*] User can log out of his or her account
- [*] User can take a photo, add a caption, and post it to "Instagram"
- [*] User can view the last 20 posts submitted to "Instagram"
- [*] User can pull to refresh the last 20 posts submitted to "Instagram"
- [*] User can tap a post to go to a Post Details activity, which includes timestamp and caption.

The following **stretch** features are implemented:

- [*] Style the login page to look like the real Instagram login page.
- [*] Style the feed to look like the real Instagram feed.
- [*] User can load more posts once he or she reaches the bottom of the feed using endless scrolling.
- [*] User should switch between different tabs using fragments and a Bottom Navigation View.
  - [*] Feed Tab (to view all posts from all users)
  - [*] Capture Tab (to make a new post using the Camera and Photo Gallery)
  - [*] Profile Tab (to view only the current user's posts, in a grid)
- [*] Show the username and creation time for each post
- User Profiles:
  - [*] Allow the logged in user to add a profile photo
  - [*] Display the profile photo with each post
  - [ ] Tapping on a post's username or profile photo goes to that user's profile page
  - [*] User Profile shows posts in a grid
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [*] User can like a post and see number of likes for each post in the post details screen.

The following **additional** features are implemented:

- [*] Custom Toolbar holds a Logout button
- [*] Animated transitions when going from feed to Post detail activity

Please list two areas of the assignment you'd like to **discuss further with your peers** during the next class (examples include better ways to implement something, how to extend your app in certain ways, etc):

1. Some areas of code have to be duplicated, such as the like buttons in the feed and post detail or adapter functions - is there a better way to modularize code like a button onClick so it can be reused?
2. I didn't have time to add comments, but I'd love to see others' implementations and how they did it.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/).

## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Default Profile Image](https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png)
- [Endless Scroll View](https://gist.github.com/nesquena/d09dc68ff07e845cc622)



## Notes

1. The app uses a one activity/many fragments structure, which raised a challenge while opening the "EditFragment" to upload a profile photo.
Since fragments can't be started from other fragments using activities, I used a transaction instead, which doesn't have an "onActivityResult" method.
Without that method, there was no place to call an update to the original profile picture once the new one was uploaded. I could have created an "EditActivity"
instead of a fragment to solve this, but for now, the profile pic updates if you navigate away and back to the profile screen.
2. Likes are not persisted after refresh - a TA told us it was impossible to achieve this with our current app structure.
3. As mentioned above, there were some places I duplicated code because I couldn't think of a way to share it: for example, the adapters for the feed and
profile pages are similar, but they require different layout files, so I separated them into two. Another example is the like button onClickListener, which is
duplicated between the home feed and post detail view.
