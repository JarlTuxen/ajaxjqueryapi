function getCars() {
    console.log("GET car");
    //Ajax request - hent biler
    $.ajax({
        url:"/car",
        type:"GET",
        contentType:"application/JSON",
        success:function (data) {
            $.each(data, function (index, item) { //iterer over collection i data
                $("#carList").append("<div>" + item.name + "</div>")
            })
            $("#status").html("<br>Svar fra server OK");
        },
        error:function (data) {
            $("#status").html("<br>Svar fra server ERROR");
        }
    });
}

function preventSubmit(form){
    form.submit(function(event){
       event.preventDefault(); // forhindr formen i at blive sendt, når der trykkes submit
       createCar($("#name").val()); // tekst i inputfeltet
    });
}

function createCar(carName){
    var createCarObject = {};
    createCarObject["name"] = carName;
    console.log("Create " + carName);
    $.ajax({
       url:"/car",
       type:"POST",
       contentType:"application/JSON",
       data:JSON.stringify(createCarObject),
       success:function(data){
           console.log("Car created: " + data.name);
           $("#carList").append("<div>" + data.name + "</div>");
           $("#status").html("<br>Svar fra server OK");
       },
        error:function(data){
           $("#status").html("<br>Svar fra server ERROR");
        }
    });
}
