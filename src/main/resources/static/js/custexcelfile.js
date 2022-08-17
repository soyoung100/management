
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
		url: '/custexcelfile',
		data: excelData,
		method: 'POST',
		contentType: 'application/json',
		dataType: '',
		success: function(data) {
            showExcel(excelData);
			alert("데이터가 성공적으로 저장되었습니다.");
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

