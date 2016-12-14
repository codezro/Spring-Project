$(document).ready(function () {

    $("#checkbox").change(function(){
        var anonymous = $('#anonymous');
        anonymous.val()== "0" ? anonymous.val("1") : anonymous.val("0");
    });

    function validator() {
        jQuery.validator.setDefaults({
            debug: true,
            success: "valid"
        });

        $("#registerForm").validate({
            rules:{
                firstname: {
                    required: true
                },
                lastname: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                mobileNumber: {
                    required: true,
                    number: true,
                    digits: true
                }
            },
            messages:{
                firstname: "Please enter your firstname",
                lastname: "Please enter your lastname",
                email: {
                    required: "Email is required",
                    email: "Email is invalid"
                },
                mobileNumber: {
                    required: "Please provide a Mobile Number",
                    number: "Please enter numbers only",
                    digits: "Please enter numbers only"
                }
            }
        });
    }

    function submit() {
        $.ajax({
            type : 'post',
            url : '/main',
            data : $("#registerForm").serialize(),
            success: function (response){
                var data = response.errors;

                $("#message").removeClass("message-error").html("");
                $("#err_firstname").html("");
                $("#err_lastname").html("");
                $("#err_email").html("");
                $("#err_mobileNumber").html("");
                $("#err_message").html("");

                if(response.hasError == true) {

                    circleOnThirdPart.removeClass("round-color");
                    circleOnFirstPart.addClass("round-color");
                    thirdPart.hide();
                    firstPart.show();
                    if(response.isDuplicate == true){
                        $("#err_message").html("Email Address is already exist.");
                    }
                    for (var i =0;i<=data.length;i++){
                        $("#err_"+data[i].field).html(data[i].defaultMessage);
                    }
                }else {
                    $("#firstname").val("");
                    $("#lastname").val("");
                    $("#email").val("");
                    $("#number").val("");
                    $(".form-first-page-div").hide();
                    var message = $(".first-page-div").append(".second-page-div");
                    message.addClass("successMessage").text("Successfully Registered!");
                }
            },
            error: function(){
                alert("error");
            }
        });
    }

    var toBeHiddenOnLoad = $("#third-part-form, #second-part-form, #second-button-div, #third-button-div ");
	var firstPart = $("#first-part-form, #first-button-div");
	var secondPart = $("#second-part-form, #second-button-div");
	var thirdPart = $("#third-part-form, #third-button-div");
	var circleOnFirstPart = $(".round-one-div");
	var circleOnSecondPart = $(".round-two-div");
	var circleOnThirdPart = $(".round-three-div");
	toBeHiddenOnLoad.hide();
	circleOnFirstPart.addClass("round-color");
    $(".button-next").click(function (e) {

        validator();

        var form = $("#registerForm").valid();

        if(form){
            firstPart.hide();
            secondPart.show();
            circleOnFirstPart.removeClass("round-color");
            circleOnSecondPart.addClass("round-color");
        }

    });
	$(".button-proceed").click(function (e) {
		secondPart.hide();
		thirdPart.show();
		circleOnSecondPart.removeClass("round-color");
		circleOnThirdPart.addClass("round-color");
	});
	$(".button-back").click(function (e) {
		var hasRoundColor = $(".round-three-div");
		if(hasRoundColor.hasClass("round-color")){
			circleOnThirdPart.removeClass("round-color");
			circleOnSecondPart.addClass("round-color");
			thirdPart.hide();
			secondPart.show();
		}else{
			circleOnSecondPart.removeClass("round-color");
			circleOnFirstPart.addClass("round-color");
			secondPart.hide();
			firstPart.show();
		}
	});


	$('.button-submit').click(function (event) {
		event.preventDefault();
		submit();
	});

});