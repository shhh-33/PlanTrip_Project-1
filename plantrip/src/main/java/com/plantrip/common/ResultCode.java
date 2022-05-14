package com.plantrip.common;

public enum ResultCode {

    /**
     * ResultCode로 리턴할 코드와 메세지를 해당 클래스에 정의
     * 1000 번대 : user error
     * 2000 번대 : trip error
     * 3000 번대 : attendee error
     * 9999 번태 : 기타
     */
    Success(0, "성공"),

    NOT_EXISTS_USER(1000, "존재하지 않는 회원입니다."),
    NOT_CORRECT_MEMBER(1001, "아이디와 비밀번호가 일치하지 않습니다."),

    NOT_EXISTS_TRIP(2000, "존재하지 않는 여행입니다."),

    NOT_EXISTS_ATTENDEE(3000, "존재하지 않는 참가자입니다."),
    ALREADY_EXISTS_ATTENDEE(3001, "이미 참가된 참가자입니다."),

    DBError(9998, "DB 오류입니다."),
    ETCError(9999, "기타 오류입니다.");

    ResultCode(int returnCd, String returnMsg) {
        this.returnCd = returnCd;
        this.returnMsg = returnMsg;
    }

    private int returnCd;
    private String returnMsg;

    public int getRtnCd() {
        return returnCd;
    }

    public String getRtnMsg() {
        return returnMsg;
    }

    public <T> Result<T> result(T resultObject) {
        return new Result<T>(resultObject, this);
    }


    public <T> Result<T> result() {
        return new Result<>(null, this);
    }

    @Override
    public String toString() {
        return "{" +
                "rtnCd=" + returnCd +
                ", rtnMsg='" + returnMsg + '\'' +
                '}';
    }
}
