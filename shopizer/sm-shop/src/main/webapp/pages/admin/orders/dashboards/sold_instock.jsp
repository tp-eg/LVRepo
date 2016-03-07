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
      text: "Sold/Instock"   
      },
      axisY:{
        title:"Coal (mn tonnes)"   
      },
        theme: "theme3",
      animationEnabled: true,
      data: [
      {        
        type: "stackedColumn",
        toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}mn tonnes",
        name: "Anthracite and Bituminous",
        showInLegend: "true",
        dataPoints: [
        {  y: 111338 , label: "USA"},
        {  y: 49088, label: "Russia" },
        {  y: 62200, label: "China" },
        {  y: 90085, label: "India" },
        {  y: 38600, label: "Australia"},
        {  y: 48750, label: "SA"}
        
        ]
      },  {        
        type: "stackedColumn",
        toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}mn tonnes",
        name: "SubBituminous and Lignite",
        showInLegend: "true",
        dataPoints: [
        {  y: 135305 , label: "USA"},
        {  y: 107922, label: "Russia" },
        {  y: 52300, label: "China" },
        {  y: 3360, label: "India" },
        {  y: 39900, label: "Australia"},
        {  y: 0, label: "SA"}
        
        ]
      }            
      ]
      ,
      legend:{
        cursor:"pointer",
        itemclick: function(e) {
          if (typeof (e.dataSeries.visible) ===  "undefined" || e.dataSeries.visible) {
	          e.dataSeries.visible = false;
          }
          else
          {
            e.dataSeries.visible = true;
          }
          chart.render();
        }
      }
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
      					

      			     
      			     
 <div id="chartContainer" style="height: 350px; width: 100%;">
      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     