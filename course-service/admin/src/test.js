
const { find, filter } = require('lodash');
const { makeExecutableSchema } = require('graphql-tools');

const typeDefs = '';

const authors = [
    { id: 1, firstName: 'Tom', lastName: 'Coleman' },
    { id: 2, firstName: 'Sashko', lastName: 'Stubailo' },
    { id: 3, firstName: 'Mikhail', lastName: 'Novikov' },
];

const posts = [
    { id: 1, authorId: 1, title: 'Introduction to GraphQL', votes: 2 },
    { id: 2, authorId: 2, title: 'Welcome to Apollo', votes: 3 },
    { id: 3, authorId: 2, title: 'Advanced GraphQL', votes: 1 },
    { id: 4, authorId: 3, title: 'Launchpad is Cool', votes: 7 },
];
const topicBanks = [
    {id:1,name:'1'}
];
const topics = [
    {id:1,name:'1'}
];
const topicOptions= [
    {id:1,name:'1'}
];
const resolvers = {
    Query: {
        posts() {
            return posts;
        },
        topicBanks() {
            return topicBanks;
        },
        topics() {
            return topics;
        },
        topicOptions() {
            return topicOptions;
        }
    },
    Mutation: {
        upvotePost(_, { postId }) {
            const post = find(posts, { id: postId });
            if (!post) {
                throw new Error(`Couldn't find post with id ${postId}`);
            }
            post.votes += 1;
            return post;
        },
    },
    Author: {
        posts(author) {
            return filter(posts, { authorId: author.id });
        },
    },
    Post: {
        async author(post) {
            return await new Promise(resolve => {
                setTimeout(()=> {
                    resolve(find(authors, { id: post.authorId }));
                },3000);
            })
        },
    },
    Topic: {
        topicOptions(root){
            return topicOptions;
        },
        _sid() {
            return '1';
        }
    },
    TopicBank: {
        topics(root,{id}) {
            console.log(root,id);
            return topics;
        },
        _sid() {
            return '1';
        }
    },

};
const executableSchema = makeExecutableSchema({
    typeDefs,
    resolvers,
});
module.exports = {executableSchema};