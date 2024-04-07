$(document).ready(function () {
    $.getJSON("/v1/Sectors/GetAllSectors", function (data) {
        $.each(data, function (index, sector) {
           $('select[name="sectorsOptions[]"]').append('<option value= "' + sector.id + '">' + sector.name + '</option>');
        });
    });

    $.getJSON("v1/Sectors/GetSectorsForUser", function (data) {
        $.each(data, function (index, userSector) {
           $('#sectorsOptions').find('option[value="' + userSector.id + '"]').prop('selected', true);
        });
    });

    $('#sectorsOptions').on('mousedown', 'option', function (e) {
        e.preventDefault();
        var isSelected = $(this).prop('selected');
        $(this).prop('selected', !isSelected);
        return false;
    });

    $('#sectorsForm').submit(function (e) {
        e.preventDefault();

        var selectedSectors = $('#sectorsOptions').val();

        $.ajax({
            url:'/v1/Sectors/AddUserSector',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(selectedSectors)
        });
    });
});