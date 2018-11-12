"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();
// Start writing Firebase Functions
// https://firebase.google.com/docs/functions/typescript
// Listens for new messages added to /messages/:pushId/original and creates an
// uppercase version of the message to /messages/:pushId/uppercase
exports.thongBaoUngVienApply = functions.database.ref('/choDuyets/{companyId}/{idJob}/{idUngVien}')
    .onCreate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const companyId = context.params.companyId;
    const idJob = context.params.idJob;
    //const jobId = snapshot.key
    const idUngVien = context.params.idUngVien;
    // const id1 = snapshot.ref.child("idUngVien").once('value')
    //const id2 = snapshot.child("idUngVien").key
    //  const ungVienId = context.params.idUngvien
    //const keyUngVien = snapshot.child("idUngVien").key
    //const companyId = snapshot.ref.parent.key
    //const nameUngVien : string
    console.log('companyId: ' + companyId + ',jobId: ' + idJob + ",idungvien: " + idUngVien);
    const getNameUngVien = admin.database().ref('/jobseekers/' + idUngVien + '/email').once('value');
    // .then(nameUngVienSn => {
    //     nameUngVien = nameUngVienSn.val()
    // });
    const getTinTuyenDung = admin.database().ref('/tinTuyenDungs/' + companyId + '/' + idJob + '/nameJob').once('value');
    const results = yield Promise.all([getNameUngVien, getTinTuyenDung]);
    const nameSnapshot = results[0];
    const tinTuyenDungSnapshot = results[1];
    const nameUngVien = nameSnapshot.val();
    const tinTuyenDung = tinTuyenDungSnapshot.val();
    console.log('name: ' + nameUngVien + ' tin: ' + tinTuyenDung);
    const payload = {
        notification: {
            title: 'Co ung vien apply',
            body: 'ung vien ' + nameUngVien + ' đã apply ' + tinTuyenDung + ' của bạn',
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
exports.thongBaoPheDuyetNhaTuyenDung = functions.database.ref('/companys/{companyId}/approvalMode')
    .onUpdate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const companyId = context.params.companyId;
    const approvalMode = admin.database().ref('/companys/' + companyId + '/approvalMode').once('value');
    const results = yield Promise.all([approvalMode]);
    const approvalModeSnapshot = results[0];
    const isApproval = parseInt(approvalModeSnapshot.val());
    let payload;
    console.log('companyId: ' + companyId + ', Approval mode: ' + isApproval);
    if (isApproval === 1) {
        payload = {
            notification: {
                title: 'Phê duyệt hồ sơ thành công',
                body: 'Hồ sơ của bạn trên Job IT đã được thành công',
                badge: '1',
                sound: 'default'
            }
        };
    }
    else {
        payload = {
            notification: {
                title: 'Phê duyệt hồ sơ thất bại',
                body: 'Hồ sơ trên Job IT của bạn không được duyệt. Bạn hãy kiểm tra lại.',
                badge: '1',
                sound: 'default'
            }
        };
    }
    return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
//# sourceMappingURL=index.js.map