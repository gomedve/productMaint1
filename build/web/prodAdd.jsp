<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
    
<h1>Product</h1>

<form action="productMaint" method="post">
  <label for="prodCode">Code:</label> 
    <input type="text" name="prodCode" value="<c:out value="${product.code}" />" required /><br />
  <label for="prodDescription">Description:</label> 
    <input type="text" name="prodDescription" size="50" value="<c:out value="${product.description}" />" required /><br />
  <label for="prodPrice">Price:</label> 
    <input type="text" name="prodPrice" value="<c:out value="${product.price}" />" required /><br />

  <input type="hidden" name="action" value="addConfirmed">
  <input type="submit" value="Update Product">
</form>

<form action="productMaint" method="post">
  <input type="hidden" name="action" value="displayProducts">
  <input type="submit" value="View Products">
</form>

</body>
</html>