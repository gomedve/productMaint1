<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
    
<h1>Are you sure you want to delete this product?</h1>

<table class="noBorder">
  <tr>
    <td><label>Code:</label></td>
    <td><c:out value="${product.code}" /></td>
  </tr>
  <tr>
    <td><label>Description:</label></td>
    <td><c:out value="${product.description}" /></td>
  </tr>
  <tr>
    <td><label>Price:</label></td>
    <td><c:out value="${product.price}" /></td>
  </tr>
</table>

<table class="noBorder">
  <tr>
    <td>
      <form action="" method="post">
        <input type="hidden" name="action" value="deleteConfirmed">
        <input type="submit" value="Yes">
      </form>
    </td>
    <td>
      <form action="" method="post">
        <input type="hidden" name="action" value="displayProducts">
        <input type="submit" value="No">
      </form>
    </td>
  </tr>
</table>
  
</body>
</html>