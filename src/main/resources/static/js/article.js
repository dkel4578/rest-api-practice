// 등록 버튼 이벤트 기능
var createBtn = document.getElementById('create-btn');
var moveBtn = document.getElementById('move-btn');
var modifyBtn = document.getElementById('modify-btn');
var deleteBtn = document.getElementById('delete-btn');
let params = new URLSearchParams(location.search);
let id = params.get('id');
console.log(params);
if (createBtn) {
    createBtn.addEventListener('click', evt => {
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(data => {
            alert('등록 완료되었습니다.');
            location.replace("/articles");     // articles로 화면으로 이동
        })

    })
}

if(moveBtn){
    moveBtn.addEventListener('click', evt =>{
        location.replace("/articles")
    })
}

if(modifyBtn){
    modifyBtn.addEventListener('click', evt =>{

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers : {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content : document.getElementById('content').value
            })
        }).then(data => {
        alert('수정 완료되었습니다.')
        location.replace("/articles");
        })
    })
}

if(deleteBtn){
    deleteBtn.addEventListener('click', evt =>{
    if(alert('삭제하시겠습니까?')){
      fetch('/api/articles/{id}', {
                method : 'DELETE',
                headers : {
                    "Content-Type" : "application/json",
                },
               body: JSON.stringify({
                    title: document.getElementById('title').value,
                    content : document.getElementById('content').value
               })
            }).then(data =>{
            alert('삭제되었습니다.')
            location.replace("/articles");
            })
            }
      })
    }
