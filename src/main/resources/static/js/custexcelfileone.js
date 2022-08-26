window.onload = function() {
	let url = new URL(window.location.href);
	let urlParams = url.searchParams;
	let urlParamIdx = urlParams.get('name');

    $.ajax({
		url: '/cust/excelfile/list/one/content?name='+urlParamIdx,
		data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            showJsonContent(data);
		},
		error: function(jqXHR, textStatus) {
			alert("에러가 발생했습니다. \n에러 내용: " + textStatus);
		}
	})
}

function showJsonContent(data) {
	let length = data.length;
	let domJsonContent = document.getElementById('jsonContent');

	for (var i = 0; i < length; i++) {
		let newP = document.createElement("p");
		newP.innerHTML = data[i];
		domJsonContent.append(newP);
	}
}