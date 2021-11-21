let Attain = {
    init : function () {
        Attain.config();
        Attain.button_init();
        Attain.ViewDataInt();
        Attain.dataTableIni();
        Attain.goDataList();
    },

    config : function () {

        return false;
    },

    button_init : function () {
        $('#select_all').on('click', function(){
            let rows = $('#dataTable').DataTable().rows({ 'search': 'applied' }).nodes();
            $('input[type="checkbox"]', rows).prop('checked', this.checked);
        });

        $("#searchBtn").on("click", function(ev) {
            Attain.goDataList();
            return false;
        });

        $("#excelBtn").on("click", function(ev) {
            Attain.downloadExcel();
            return false;
        });
    },

    downloadExcel : function () {
        const url = '/incom/attainApi/dataListExcel';
        let f = document.dataFrm;
        f.action = url;
        f.submit();
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
                    'targets': 0,
                    'searchable': false,
                    'orderable': false,
                    'className': 'dt-body-center alCenter',
                    'render': function (data, type, full, meta){
                        return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
                    }
                },
                {
                    'targets': [4,5,6,7,8,9],
                    'className': 'alRight',
                },
            ],
            order: [[0, 'asc']],
            responsive: true,
            bInfo: false,
            lengthMenu: [20, 40, 60, 80]
        });

        $('#dataTable tbody').on('click', 'tr', function () {
            let data = $('#dataTable').DataTable().row(this).data();
            $("#dataTable tr").not(this).removeClass('selected');
            $(this).addClass('selected');
            console.log(data);
            Attain.ViewData(data);
        });

        $('#dataTable tbody').on('change', 'input[type="checkbox"]', function(){
            if ( !this.checked ) {
                let el = $('#select_all').get(0);
                if ( el && el.checked && ('indeterminate' in el) ) {
                    el.indeterminate = true;
                }
            }
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
            url: '/incom/attainApi/dataList',
            type: 'post',
            data: param,
            success: function (data) {
                console.log(data);
                tableGrid.clear().draw();
                let rowData = data.dataList;
                $.each(rowData, function (key) {
                    tableGrid.row.add([
                        rowData[key].flgpercent,
                        rowData[key].iclass,
                        rowData[key].ingcd,
                        rowData[key].ingnm,
                        rowData[key].capacity,
                        rowData[key].nowcnt,
                        rowData[key].moncnt,
                        rowData[key].flgcnt,
                        rowData[key].flgpercentval,
                        rowData[key].reqcnt,
                    ]).draw(false);
                });
            },
            error: function (data) {
                console.log(data);
            }
        });
    },
}