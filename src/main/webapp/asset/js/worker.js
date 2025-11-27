// ==============================
// 🕐 worker.js (Web Worker)
// ==============================

// 이 파일은 메인 스레드(JS 본문)와 별도로 실행됨.
// setInterval을 여기서 돌리면 메인화면이 멈추거나 끊길 일이 없음.

// 타이머 ID (setInterval 핸들)
let timerId = null;

// 운동 시작 시각 (밀리초 단위)
let startTime = 0;

// 메인 스레드에서 메시지를 받았을 때 실행되는 함수
onmessage = function (e) {
  const { type } = e.data; // type 값에 따라 분기 (start / stop)

  switch (type) {
    case 'start-timer':
      // 운동 시작 시 현재 시각 기록
      startTime = Date.now();

      // 1초마다 현재 시각 - 시작 시각 = 경과 시간(ms) 계산
      timerId = setInterval(() => {
        const now = Date.now();
        const elapsed = now - startTime; // 경과 시간 (밀리초)
        
        // 메인 스레드(runningmain.jsp)로 경과 시간 전달
        postMessage({ type: 'timer', elapsed });
      }, 1000);
      break;

    case 'stop-timer':
      // 운동 정지 시 타이머 중단
      if (timerId) {
        clearInterval(timerId);
        timerId = null;
      }

      // 종료 신호 메인으로 전달
      postMessage({ type: 'timer-end' });
      break;
  }
};
