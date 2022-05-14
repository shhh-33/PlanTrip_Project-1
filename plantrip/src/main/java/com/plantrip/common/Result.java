package com.plantrip.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
    Result 클래스 : <리턴할 오브젝트 결과물 + 결과물에 대한 상태 코드> 를 묶어서 가져오기 (Exception보다 가시성이 좋다)
    제네릭 타입 <T> : 모든 타입의 변수들을 담을 수 있도록 임의 타입T로 미리 선언해 둔 것
 
    -> 따라서 Service단에서 가져오는 결과물은 모두 Result<>로 묶어서 가져오기
 */
public class Result<T> {

    private final T resultObject; //DB에서 가져온 결과물
   private final ResultCode resultCode; //상태 코드

    public Result(T resultObject, ResultCode resultCode) {
        this.resultObject = resultObject;
        this.resultCode = resultCode;
    }

    //result만 리턴
    public T getResultObject() {
        return resultObject;
    }

    //int타입인 code만 리턴
    public int getRtnCd() {
        return resultCode.getRtnCd();
    }

    //string타입인 code의 메세지만 리턴
    public String getRtnMsg() {
        return resultCode.getRtnMsg();
    }


    @JsonIgnore // 해당값이 json 결과창에서 보이지 않도록 설정
    public ResultCode getResultCode() {
        return resultCode;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return ResultCode.Success.equals(resultCode);
    }

    @JsonIgnore
    public boolean isNotSuccess() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "{" +
                "resultObject=" + resultObject +
                ", resultCode=" + resultCode +
                '}';
    }
}
