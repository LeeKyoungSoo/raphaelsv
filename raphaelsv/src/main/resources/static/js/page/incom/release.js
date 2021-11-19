let Release = {
    init : function () {
        Release.config();
        Release.button_init();
        Release.ViewDataInt();
        Release.dataTableIni();
        Release.goDataList();
        Common.goPharmaList();
    },

    config : function () {
        $("#stdt").datepicker('setDate', '-3M');
        $("#endt").datepicker('setDate', 'today');
        return false;
    },

    button_init : function () {
        $("#searchBtn").on("click", function(ev) {
            Release.goDataList();
            return false;
        });
    },

    dataTableIni : function () {
        $('#dataTable').DataTable({
            scrollY: "380px",
            scrollCollapse: true,
            scrollX: false,
            // 표시 건수기능 숨기기
            lengthChange: true,
            // 검색 기능 숨기기
            searching: false,
            // 정렬 기능 숨기기
            ordering: true,
            // 정보 표시 숨기기
            info: true,
            // 페이징 기능 숨기기
            paging: true,
            columnDefs: [
                {
                    "targets": [8,9,10],
                    "visible": false,
                    "searchable": false
                },
                {
                    'targets': [0],
                    'className': 'alCenter',
                },
                {
                    'targets': [3,6],
                    'className': 'alRight',
                },
            ],
            responsive: true,
            bInfo: false,
            lengthMenu: [20, 40, 60, 80]
        });

        $('#dataTable tbody').on('click', 'tr', function () {
            let data = $('#dataTable').DataTable().row(this).data();
            $("#dataTable tr").not(this).removeClass('selected');
            $(this).addClass('selected');
            console.log(data);
            Release.ViewData(data);
        });

    },

    ViewData : function (data) {

    },

    ViewDataInt : function () {

    },

    goDataList : function () {
        let tableGrid = $('#dataTable').DataTable();
        let param = $("form[name=dataFrm]").serialize();
        $.ajax({
            url: '/incom/releaseApi/dataList',
            type: 'post',
            data: param,
            success: function (data) {
                console.log(data);
                tableGrid.clear().draw();
                let rowData = data.dataList;
                $.each(rowData, function (key) {
                    tableGrid.row.add([
                        rowData[key].regdt,
                        rowData[key].ingcd,
                        rowData[key].ingnm,
                        rowData[key].capacity,
                        rowData[key].phanm,
                        rowData[key].prdnm,
                        rowData[key].relcnt,
                        rowData[key].bigo,
                        rowData[key].incomcd,   //9
                        rowData[key].iclass,
                        rowData[key].phacd,
                    ]).draw(false);
                });
            },
            error: function (data) {
                console.log(data);
            }
        });
    },
}