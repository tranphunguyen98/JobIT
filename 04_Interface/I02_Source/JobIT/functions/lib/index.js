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
    // return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
    // .then(fcm_token => {
    //         console.log('token available : ' + fcm_token.val())
    //         return admin.messaging().sendToDevice(fcm_token.val(),payload)
    // })
    return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
    // return Promise.all
}));
// exports.thongBaoChapNhanUngVien = functions.database.ref('/choDuyets/{companyId}/{idJob}')
// .onCreate(async(snapshot, context) => {
//     const companyId = context.params.companyId
//     const idJob = context.params.idJob
//     const idUngVien = snapshot.child("idUngVien").val()
//     //const nameUngVien : string
//     console.log('companyId: ' + companyId + ',jobId: ' + idJob + ",idungvien: " + idUngVien)
//     const getNameUngVien = admin.database().ref('/jobseekers/' + idUngVien + '/email').once('value')
//     const getTinTuyenDung = admin.database().ref('/tinTuyenDungs/' + companyId + '/' + idJob + '/nameJob').once('value')
//     const results = await Promise.all([getNameUngVien,getTinTuyenDung])
//     const nameSnapshot = results[0]
//     const tinTuyenDungSnapshot = results[1]
//     const nameUngVien = nameSnapshot.val()
//     const tinTuyenDung = tinTuyenDungSnapshot.val()
//     console.log('name: ' + nameUngVien + ' tin: ' + tinTuyenDung)
//     const payload = {
//         notification:{
//             title: 'Co ung vien apply',
//             body: 'ung vien ' + nameUngVien + ' đã apply ' + tinTuyenDung + ' của bạn', 
//             badge: '1',
//             sound: 'default'
//         }
//     }
//     return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
//     .then(fcm_token => {
//             console.log('token available : ' + fcm_token.val())
//            return admin.messaging().sendToDevice(fcm_token.val(),payload)
//     })
//     // return Promise.all
// })
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
exports.thongBaoCanhCaoJobseeker = functions.database.ref('/reports/jobseekers/{idUser}/{idReport}/isWarned')
    .onUpdate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const idUser = context.params.idUser;
    const idReport = context.params.idReport;
    const adminCommentData = admin.database().ref('/reports/jobseekers/' + idUser + '/' + idReport + '/adminComment').once('value');
    console.log('jobSeekerId: ' + idUser + ', idReport ' + idReport, ', adminComment: ' + adminCommentData);
    const results = yield Promise.all([adminCommentData]);
    const dataSnapshot = results[0];
    const adminComment = dataSnapshot.val();
    const payload = {
        notification: {
            title: 'Cảnh cáo',
            body: adminComment,
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/' + idUser + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
exports.thongBaoCanhCaoRecruiter = functions.database.ref('/reports/recruiters/{idUser}/{idReport}/isWarned')
    .onUpdate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const idUser = context.params.idUser;
    const idReport = context.params.idReport;
    const adminCommentData = admin.database().ref('/reports/recruiters/' + idUser + '/' + idReport + '/adminComment').once('value');
    console.log('recruitersID: ' + idUser + ', idReport ' + idReport, ', adminComment: ' + adminCommentData);
    const results = yield Promise.all([adminCommentData]);
    const dataSnapshot = results[0];
    const adminComment = dataSnapshot.val();
    const payload = {
        notification: {
            title: 'Cảnh cáo',
            body: adminComment,
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/' + idUser + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
exports.thongBaoAdminToCaoRecruiterMoi = functions.database.ref('/reports/recruiters/{idUser}/{idReport}')
    .onCreate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const idUser = context.params.idUser;
    const name = admin.database().ref('/companys/' + idUser + '/name').once('value');
    console.log('id user: ' + idUser + ', name user: ' + name);
    const results = yield Promise.all([name]);
    const dataSnapshot = results[0];
    const nameCompany = dataSnapshot.val();
    const payload = {
        notification: {
            title: 'Tố cáo mới',
            body: 'Đơn vị bị tố cáo: ' + nameCompany,
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/Xf48VViAaoMAcNvgWvveiDepiq02' + '/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
exports.thongBaoAdminToCaoJobSeekerMoi = functions.database.ref('/reports/jobseekers/{idUser}/{idReport}')
    .onCreate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const idUser = context.params.idUser;
    const name = admin.database().ref('/jobseekers/' + idUser + '/name').once('value');
    console.log('id user: ' + idUser + ', name user: ' + name);
    const results = yield Promise.all([name]);
    const dataSnapshot = results[0];
    const nameJobseeker = dataSnapshot.val();
    const payload = {
        notification: {
            title: 'Tố cáo mới',
            body: 'Người bị tố cáo: ' + nameJobseeker,
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/Xf48VViAaoMAcNvgWvveiDepiq02/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
exports.thongBaoAdminHoSoMoi = functions.database.ref('/companysWaitingAdminApproval/{idCompany}')
    .onCreate((snapshot, context) => __awaiter(this, void 0, void 0, function* () {
    const idCompany = context.params.idCompany;
    const name = admin.database().ref('/companys/' + idCompany + '/name').once('value');
    console.log('id company: ' + idCompany + ', name company: ' + name);
    const results = yield Promise.all([name]);
    const dataSnapshot = results[0];
    const nameCompany = dataSnapshot.val();
    const payload = {
        notification: {
            title: 'Hồ sơ cần duyệt mới',
            body: 'Tên công ty: ' + nameCompany,
            badge: '1',
            sound: 'default'
        }
    };
    return admin.database().ref('/fcm_tokens/Xf48VViAaoMAcNvgWvveiDepiq02/token').once('value')
        .then(fcm_token => {
        console.log('token available : ' + fcm_token.val());
        return admin.messaging().sendToDevice(fcm_token.val(), payload);
    });
}));
//# sourceMappingURL=index.js.map