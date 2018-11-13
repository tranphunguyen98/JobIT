const algoliasearch = require('algoliasearch');
const firebase = require('firebase');

// configure firebase
firebase.initializeApp({
  databaseURL: "https://jobit-e18f7.firebaseio.com"
});
const database = firebase.database();

// configure algolia
const algolia = algoliasearch(
  "OYGFHT3AFC",
  "5d4c2ba1711e8b20d3754bc717222edc"
);
const index = algolia.initIndex("tinTuyenDungs");

// Get all contacts from Firebase
database.ref('/tinTuyenDungs').once('value', tinTuyenDungs => {
  // Build an array of all records to push to Algolia
  const records = [];
  tinTuyenDungs.forEach(tinTuyenDung => {
    tinTuyenDung.forEach(tin => {
       // get the key and data from the snapshot
    const childKey = tin.key;
    const childData = tin.val();
    // We set the Algolia objectID as the Firebase .key
    childData.objectID = childKey;
    // Add object for indexing
    records.push(childData);
  })   
  });

  // Add or update new objects
  index
    .saveObjects(records)
    .then(() => {
      console.log('Contacts imported into Algolia');
    })
    .catch(error => {
      console.error('Error when importing contact into Algolia', error);
      process.exit(1);
    });
});

const tinTuyenDungsRef = database.ref('/tinTuyenDungs');
tinTuyenDungsRef.on('child_added', addOrUpdateIndexRecord);
tinTuyenDungsRef.on('child_changed', addOrUpdateIndexRecord);
tinTuyenDungsRef.on('child_removed', deleteIndexRecord);

function addOrUpdateIndexRecord(tinTuyenDung) {
  tinTuyenDung.forEach(tin => {
    // Get Firebase object
  const record = tin.val();
  // Specify Algolia's objectID using the Firebase object key
  record.objectID = tin.key;
  record.Add
  // Add or update object
  index
    .saveObject(record)
    .then(() => {
      console.log('Firebase object indexed in Algolia', record.objectID);
    })
    .catch(error => {
      console.error('Error when indexing contact into Algolia', error);
      process.exit(1);
    });
  })
  
}

function deleteIndexRecord(tinTuyenDung) {
  tinTuyenDung.forEach(tin => {
     // Get Algolia's objectID from the Firebase object key
  const objectID = tin.key;
  // Remove the object from Algolia
  index
    .deleteObject(objectID)
    .then(() => {
      console.log('Firebase object deleted from Algolia', objectID);
    })
    .catch(error => {
      console.error('Error when deleting contact from Algolia', error);
      process.exit(1);
    });
  })
 
}