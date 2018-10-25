"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();
// Start writing Firebase Functions
// https://firebase.google.com/docs/functions/typescript
// Listens for new messages added to /messages/:pushId/original and creates an
// uppercase version of the message to /messages/:pushId/uppercase
exports.hello = functions.database.ref('/jobseekers/{jobseekerId}')
    .onCreate((snapshot, context) => {
    const payload = {
        notification: {
            title: 'Message from Cloud',
            body: 'Mail: ',
            badge: '1',
            sound: 'default'
        }
    };
    const jobseekerId1 = snapshot.key;
    console.log('abc ${jobseekerId}' + jobseekerId1 + 'a ${jobseekerId1}');
    return admin.database().ref('/fcm_tokens/' + jobseekerId1 + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
});
//# sourceMappingURL=index.js.map