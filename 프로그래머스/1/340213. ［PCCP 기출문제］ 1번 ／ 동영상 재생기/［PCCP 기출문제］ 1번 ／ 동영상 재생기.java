class Solution {
    /*
    기능 1: 10초 전
    기능 2: 10초 후
    기능 3: 오프닝 건너뛰기
    
    [기능 1]
    prev를 입력할 경우 10초 전으로 이동
    10초 미만이면 영상 처음 위치
    처음 위치는 0분 0초
    
    [기능 2]
    next를 입력할 경우 10초 후로 이동
    남은시간이 10초 미만이면 마지막 위치
    마지막 위치는 동영상의 길이
    
    [기능 3]
    현재 재생 위치
    op_start<= ?? <=op_end 라면 끝나는 위치로 바로 이동
    */
    static int opStartMin, opStartSec, opEndMin, opEndSec, endMin, endSec;
    
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        String[] endPos = video_len.split(":");
        endMin = Integer.parseInt(endPos[0]);
        endSec = Integer.parseInt(endPos[1]);
        
        String[] curPos = pos.split(":");
        int curMin = Integer.parseInt(curPos[0]);
        int curSec = Integer.parseInt(curPos[1]);
        
        String[] opStartPos = op_start.split(":");
        opStartMin = Integer.parseInt(opStartPos[0]);
        opStartSec = Integer.parseInt(opStartPos[1]);
        
        String[] opEndPos = op_end.split(":");
        opEndMin = Integer.parseInt(opEndPos[0]);
        opEndSec = Integer.parseInt(opEndPos[1]);
        
        int curTime = curMin*60+curSec;
        int endTime = endMin*60+endSec;
        
        
        // command 받는거 for+switch-case로 작성
        for(int i = 0;i<commands.length;i++) {
            String command = commands[i];
            // op_start ~ op_end 사이인지 체크
            if(isInOp(curTime)) curTime = opEndMin*60+opEndSec;
            switch(command) {
                case "next":
                    if(curTime+10<endTime){
                        curTime+=10;
                    } else curTime = endTime;
                    break;
                case "prev":
                    if(curTime-10>0){
                        curTime-=10;
                    } else curTime = 0;
                    break;
            }
        }
        if(isInOp(curTime)) curTime = opEndMin*60+opEndSec;
        
        curMin = curTime/60;
        curSec = curTime%60;
        
        String curTimeStr = "";
        
        if(curMin<10) curTimeStr+="0"+curMin+":";
        else curTimeStr+=curMin+":";
        
        if(curSec<10) curTimeStr+="0"+curSec;
        else curTimeStr+=curSec;
        
        return curTimeStr;
    }
    
    private boolean isInOp(int curTime) {
        return curTime>=opStartMin*60+opStartSec && curTime<=opEndMin*60+opEndSec;
    }
}