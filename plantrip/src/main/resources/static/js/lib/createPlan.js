$createPlan = {
    create: function () {
        var sel = document.getElementById("region");
        var region = sel.options[sel.selectedIndex].value;
        var startAt = document.getElementById('startAt').value;
        if (startAt == null || startAt == '') {
            alert("시작날짜가 비어있음");
            return;
        }
        var endAt = document.getElementById('endAt').value;
        if (endAt == null || endAt == '') {
            alert("시작날짜가 비어있음");
            return;
        }
        var param = {
            startAt: startAt,
            endAt: endAt,
            region: region
        }
        $ajax.post(param, "/api/trip/save", $createPlan.createCallBack, $createPlan.createErrCallBack);
    },

    createCallBack: function (response) {
        var json = JSON.parse(response);
        if (json['rtnCd'] == 0) {   //성공
            window.location.href = "/tripList";
        } else {            //실패
            if (json['rtnMsg'] != '') {  //실패 메세지 있는경우
                alert(json['rtnMsg']);
                return;
            } else {                     //실패 메세지 없는경우
                alert("여행 등록 중 오류가 발생했습니다.");
                return;
            }
        }
    },

    createErrCallBack: function (response) {
        alert("여행 등록 중 오류가 발생했습니다.");
    }


}