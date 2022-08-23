
let columNames = ["cust_name1","cust_desc","cust_name2","status","grade","cust_code",  // 6
"cust_rename", "cust_reno", "res_no",                                                  // 3
"business_stat", "business_item", "tel_no", "tax_address", "cust_kind", "nation",      // 6
"distribution", "cust_eng_name", "fax_no", "address", "eng_address",                   // 5
" email_address", "homepage", "attached_file", "cust_explain", "cust_charge",          // 5
"create_date", "create_person",                                                        // 2
"modify_date", "modify_person",                                                        // 2
"cust_inner_code", "nation_inner_code"                                                 // 2
];

function readExcel(event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsBinaryString(file);

    reader.onload = function () {
        let data = reader.result;
        let workBook = XLSX.read(data, { type: 'binary' });
        workBook.SheetNames.forEach(function (sheetName) {
            let rows = XLSX.utils.sheet_to_json(workBook.Sheets[sheetName], 
                {header: columNames, skipHeader:true});
            sendExcelArray(rows);
        })
    };
}

function sendExcelArray(exExcelData) {

    let excelData = JSON.stringify(exExcelData);

    $.ajax({
		url: '/cust/excelfile',
		data: excelData,
		method: 'POST',
		contentType: 'application/json',
		dataType: 'text',
		success: function(data) {
            if (data == "isRunning") {
                alert("업로드된 파일이 데이터 베이스로 저장되고 있는 중입니다. 잠시 뒤에 다시 시도해주세요.");
            } else if (data == "completed") {
                showExcel(excelData);
			    alert("데이터가 성공적으로 저장되었습니다.");
            } else if (data == "isNull") {
                alert("비어있는 파일입니다. 확인 후 다시 시도해주세요.");
            } else if (data == "isKeyNull") {
                alert("적절한 파일이 아니거나, 필수 데이터가 누락된 파일입니다. 확인 후 다시 시도해주세요.");
            } else {
                alert("파일 업로드에 실패했습니다. 잠시 뒤에 다시 시도해주세요.");
            }
		},
		error: function() {
			alert("데이터를 저장하는 도중 에러가 발생했습니다.");
		}
	})

}

function showExcel(excelData) {
    let showExcelP = document.getElementById("showExcelId");
    showExcelP.innerText = excelData;
}

