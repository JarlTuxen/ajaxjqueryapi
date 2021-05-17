function getCars() {
    $("#status").append("<br>Henter biler");
    //Ajax request
    $.ajax({
        url:"/car",
        type:"GET",
        contentType:"application/JSON",
        success:function (data) {
            $.each(data, function (index, item) {
                $("#carList").append("<div>" + item.name + "</div>")
            })
            $("#status").append("<br>Svar fra server OK");
        },
        error:function (data) {
            $("#status").append("<br>Svar fra server ERROR");
        }
    });
}

function preventSubmit(form){
    form.submit(function(event){
       event.preventDefault(); // forhindr formen i at blive sendt
       createCar($("#name").val()); // tekst i inputfeltet
    });
}

function createCar(carName){
    var createCarObject = {};
    createCarObject["name"] = carName;
    $("#status").append("<br>Create " + carName);
    $.ajax({
       url:"/car",
       type:"POST",
       contentType:"application/JSON",
       data:JSON.stringify(createCarObject),
       success:function(data){
           $("#carList").append("<div>" + data.name + "</div>")
           $("#status").append("<br>Svar fra server OK");
       },
        error:function(data){
           $("#status").append("<br>Svar fra server ERROR");
        }
    });
}
