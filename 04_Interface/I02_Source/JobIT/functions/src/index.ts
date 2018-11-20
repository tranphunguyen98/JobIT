import * as functions from 'firebase-functions'
import * as admin from 'firebase-admin'
admin.initializeApp()

exports.thongBaoUngVienApply = functions.database.ref('/choDuyets/{companyId}/{idJob}/{idUngVien}')
    .onCreate(async(snapshot, context) => {
     
       
        const companyId = context.params.companyId
        const idJob = context.params.idJob

        const idUngVien = context.params.idUngVien
        
    
        const getNameUngVien = admin.database().ref('/jobseekers/' + idUngVien + '/email').once('value')
        const getTinTuyenDung = admin.database().ref('/tinTuyenDungs/' + companyId + '/' + idJob + '/nameJob').once('value')
        
        const results = await Promise.all([getNameUngVien,getTinTuyenDung])
        const nameSnapshot = results[0]
        const tinTuyenDungSnapshot = results[1]

        const nameUngVien = nameSnapshot.val()
        const tinTuyenDung = tinTuyenDungSnapshot.val()
        const timeJob = snapshot.child('timeApplied').val()

        console.log('companyId: ' + companyId + ',jobId: ' + idJob + ",idungvien: " + idUngVien + ",time "  + timeJob)

        console.log('name: ' + nameUngVien + ' tin: ' + tinTuyenDung)
        let tinTuyenDungStr : string = tinTuyenDung

        const payload = {
            notification:{
                title: 'Có ứng viên apply',
                body: 'Ứng viên ' + nameUngVien + ' đã apply ' + tinTuyenDung + ' của bạn', 
                badge: '1',
                sound: 'default'
            },
            data:{
            	type: 'thongBaoUngVienApply',
            	idCompany: companyId + '',
            	idJob: idJob + '',
            	nameJob: tinTuyenDungStr,
            	timeJob: timeJob + ''
            }

        }
        return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
        .then(fcm_token => {
                console.log('token available : ' + fcm_token.val())
               return admin.messaging().sendToDevice(fcm_token.val(),payload)
        })
    })

    // exports.thongBaoDuyetUngVienApply = functions.database.ref('/choDuyets/{companyId}/{idJob}/{idUngVien}')
    // .onCreate(async(snapshot, context) => {
     
    //     const companyId = context.params.companyId
    //     const idJob = context.params.idJob

    //     const idUngVien = context.params.idUngVien
        
    
    //     const getNameUngVien = admin.database().ref('/jobseekers/' + idUngVien + '/email').once('value')
    //     const getTinTuyenDung = admin.database().ref('/tinTuyenDungs/' + companyId + '/' + idJob + '/nameJob').once('value')
       

        
    //     const results = await Promise.all([getNameUngVien,getTinTuyenDung,getTimeJob])
    //     const nameSnapshot = results[0]
    //     const tinTuyenDungSnapshot = results[1]
    //     const timeJobSnapshot = results[2]

    //     const nameUngVien = nameSnapshot.val()
    //     const tinTuyenDung = tinTuyenDungSnapshot.val()
    //     const timeJob = timeJobSnapshot.val()

    //     console.log('companyId: ' + companyId + ',jobId: ' + idJob + ",idungvien: " + idUngVien + ",time "  + timeJob)

    //     console.log('name: ' + nameUngVien + ' tin: ' + tinTuyenDung)
    //     let tinTuyenDungStr : string = tinTuyenDung

    //     const payload = {
    //         notification:{
    //             title: 'Có ứng viên apply',
    //             body: 'Ứng viên ' + nameUngVien + ' đã apply ' + tinTuyenDung + ' của bạn', 
    //             badge: '1',
    //             sound: 'default'
    //         },
    //         data:{
    //         	type: 'thongBaoUngVienApply',
    //         	idCompany: companyId + '',
    //         	idJob: idJob + '',
    //         	nameJob: tinTuyenDungStr,
    //         	timeJob: timeJob + ''
    //         }

    //     }
    //     return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
    //     .then(fcm_token => {
    //             console.log('token available : ' + fcm_token.val())
    //            return admin.messaging().sendToDevice(fcm_token.val(),payload)
    //     })
    // })

    exports.thongBaoPheDuyetNhaTuyenDung = functions.database.ref('/companys/{companyId}/approvalMode')
    .onUpdate(async(snapshot, context) => {
        const companyId = context.params.companyId
        const approvalMode = admin.database().ref('/companys/' + companyId + '/approvalMode').once('value')

        const results = await Promise.all([approvalMode])
        const approvalModeSnapshot = results[0]
        const isApproval = parseInt(approvalModeSnapshot.val())

        let payload

        console.log('companyId: ' + companyId + ', Approval mode: '+ isApproval)
        if(isApproval  === 1){
               payload = {
                notification:{
                    title: 'Phê duyệt hồ sơ thành công',
                    body: 'Hồ sơ của bạn trên Job IT đã được thành công',
                    badge: '1',
                    sound: 'default'
                 }
                 }
        }
        else {
            payload = {
                notification:{
                    title: 'Phê duyệt hồ sơ thất bại',
                    body: 'Hồ sơ trên Job IT của bạn không được duyệt. Bạn hãy kiểm tra lại.',
                    badge: '1',
                    sound: 'default'
                 }
                 }
        }

        return admin.database().ref('/fcm_tokens/' + companyId + '/token').once('value')
        .then(fcm_token => {
                console.log('token available : ' + fcm_token.val())
                    return admin.messaging().sendToDevice(fcm_token.val(),payload)
                
            
        })
    })

    
    exports.thongBaoCanhCaoJobseeker = functions.database.ref('/reports/jobseekers/{idUser}/{idReport}/isWarned')
    .onUpdate(async(snapshot, context) => {
        const idUser = context.params.idUser
        const idReport = context.params.idReport
        const adminCommentData = admin.database().ref('/reports/jobseekers/'+ idUser+  '/'+idReport + '/adminComment').once('value')
        console.log('jobSeekerId: ' + idUser + ', idReport '+ idReport, ', adminComment: '+ adminCommentData)

        const results = await Promise.all([adminCommentData])
        const dataSnapshot = results[0]
        const adminComment = dataSnapshot.val() 

        
               const payload = {
                notification:{
                    title: 'Cảnh cáo',
                    body: adminComment,
                    badge: '1',
                    sound: 'default'
                 }
                }

        return admin.database().ref('/fcm_tokens/' + idUser + '/token').once('value')
        .then(fcm_token => {
                console.log('token available : ' + fcm_token.val())
                    return admin.messaging().sendToDevice(fcm_token.val(),payload)
        })
    })

    exports.thongBaoCanhCaoRecruiter = functions.database.ref('/reports/recruiters/{idUser}/{idReport}/isWarned')
    .onUpdate(async(snapshot, context) => {
        const idUser = context.params.idUser
        const idReport = context.params.idReport
        const adminCommentData = admin.database().ref('/reports/recruiters/'+ idUser+  '/'+idReport + '/adminComment').once('value')
        console.log('recruitersID: ' + idUser + ', idReport '+ idReport, ', adminComment: '+ adminCommentData)

        const results = await Promise.all([adminCommentData])
        const dataSnapshot = results[0]
        const adminComment = dataSnapshot.val() 

        
               const payload = {
                notification:{
                    title: 'Cảnh cáo',
                    body: adminComment,
                    badge: '1',
                    sound: 'default'
                 }
                }

        return admin.database().ref('/fcm_tokens/' + idUser + '/token').once('value')
        .then(fcm_token => {
                console.log('token available : ' + fcm_token.val())
                    return admin.messaging().sendToDevice(fcm_token.val(),payload)
        })
    })

