$ajax = {
    ajax: function (param, url, type, callbackFunc, errCallbackFunc) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () { // 요청에 대한 콜백함수
            if (xhr.readyState === xhr.DONE) { // 요청이 완료되면 실행
                if (xhr.status === 200 || xhr.status === 201) { // 응답 코드가 200 혹은 201
                    callbackFunc(xhr.responseText);
                } else {
                    errCallbackFunc(xhr.reponseText);
                }
            }
        };
        xhr.open(type, url); // http 메서드와 URL설정
        if (type == 'GET') {
            for (var key in param) {
                if (url.includes('?')) {
                    url += '&' + key + param[key];
                } else {
                    url += '?' + key + param[key];
                }
            }
            xhr.send(); // 요청 전송
        } else {
            xhr.setRequestHeader('Content-Type', 'application/json'); // 콘텐츠 타입을 json으로
            xhr.send(JSON.stringify(param)); // 요청 전송
        }
    },

    get: function (param, url, callbackFunc, errCallbackFunc) {
        $ajax.ajax(param, url, 'GET', callbackFunc, errCallbackFunc);
    },

    post: function (param, url, callbackFunc, errCallbackFunc) {
        $ajax.ajax(param, url, 'POST', callbackFunc, errCallbackFunc);
    }

}