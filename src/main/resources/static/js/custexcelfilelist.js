window.onload = firstPage();

function firstPage() {
	$.ajax({
		url: '/cust/excelfile/list/names',
		data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            showExcelList(data);
		},
		error: function(jqXHR, textStatus) {
			alert("에러가 발생했습니다. \n에러 내용: " + textStatus);
		}
	})
}

function showExcelList(data) {
    let excelFilesDiv = document.getElementById("excelFileTable");
	excelFilesDiv.innerHTML = "";
	
    for (var i = 0; i < data.length; i++) {
        let newRow = document.createElement("tr");
		let newCol = document.createElement("td");
		let contentBtn = document.createElement("button");
		let deleteBtn = document.createElement("button");
		
		// [newCol]
		newCol.innerText = data[i].name;
		// [contentBtn]
		contentBtn.innerHTML = "내용 보기";
		contentBtn.value = data[i].name;
		contentBtn.addEventListener('click', function() {
			window.open('./list/one?name=' + this.value);
		});
		// [deleteBtn]
		deleteBtn.innerHTML = "삭제";
		deleteBtn.value = data[i].name;
		deleteBtn.addEventListener('click', function() {
			let confirmChekc = confirm("파일 [ " + this.value + " ]을 삭제하시겠습니까?");
			if (confirmChekc) deleteFile(this.value);
		});

        newRow.appendChild(newCol);
        newRow.appendChild(contentBtn);
        newRow.appendChild(deleteBtn);
		excelFilesDiv.append(newRow);
    }
}

function deleteFile(value) {
	$.ajax({
		url: '/cust/excelfile/list/one?name=' + value,
		data: '',
		method: 'DELETE',
		dataType: 'text',
		success: function(data) {
			if (data == "true") {
				alert("삭제가 완료되었습니다.");
				firstPage();
			} else {
				alert("삭제가 완료되지 않았습니다. 잠시 뒤 다시 시도해주세요.")
			}
		},
		error: function(jqXHR, textStatus) {
			alert("에러가 발생했습니다. \n에러 내용: " + textStatus);
		}
	})
}