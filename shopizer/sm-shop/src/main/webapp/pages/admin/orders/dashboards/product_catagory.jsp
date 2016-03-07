<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>				
				

<script src="<c:url value="/resources/js/canvasjs/canvasjs.min.js" />"></script>


	<script type="text/javascript">
	

	window.onload = function () {
		var chart = new CanvasJS.Chart("chartContainer",
		{
			title:{
				text: "Product/Catalog"
			},
	                animationEnabled: true,
			legend:{
				verticalAlign: "center",
				horizontalAlign: "right",
				fontSize: 14,
				fontFamily: "Helvetica"        
			},
			theme: "theme1",
			data: [
			{        
				type: "pie",       
				indexLabelFontFamily: "Garamond",       
				indexLabelFontSize: 14,
				indexLabel: "{label} {y}%",
				startAngle:-20,      
				showInLegend: true,
				toolTipContent:"{legendText} {y}%",
				dataPoints: [
					{  y: 83.24, legendText:"Google", label: "Google" },
					{  y: 8.16, legendText:"Yahoo!", label: "Yahoo!" },
					{  y: 4.67, legendText:"Bing", label: "Bing" },
					{  y: 1.67, legendText:"Baidu" , label: "Baidu"},       
					{  y: 0.98, legendText:"Others" , label: "Others"}
				]
			}
			]
		});
		chart.render();
	}
	
	
	</script>
 
 

<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
			 

				 
	      			     
      					</div>
      					

      			     
      			     
<div id="chartContainer" style="height: 350px; width: 100%;"></div>

      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     