
let apiUserUrl = '/api/v1/user';
let apiRoleUrl = '/api/v1/role';
document.getElementById('btnReadUsers').addEventListener('click', getAllUsers);
document.getElementById('btnLogon').addEventListener('click', getModalCreateUser);
document.getElementById('btnCreateUser').addEventListener('click', sendCreateUser);




function sendCreateUser(){


let form = document.getElementById('userCreate').elements;

var postData={};
for (var i=0; i<formElements.length; i++)
    if (formElements[i].type!="submit")//we dont want to include the submit-buttom
        postData[formElements[i].name]=formElements[i].value;


        function getFormResults(formElement) {
          var formElements = formElement.elements;
          var formParams = {};
          var i = 0;
          var elem = null;
          for (i = 0; i < formElements.length; i += 1) {
              elem = formElements[i];
              switch (elem.type) {
                  case 'submit':
                      break;
                  case 'radio':
                      if (elem.checked) {
                          formParams[elem.name] = elem.value;
                      }
                      break;
                  case 'checkbox':
                      if (elem.checked) {
                          formParams[elem.name] = setOrPush(formParams[elem.name], elem.value);
                      }
                      break;
                  default:
                      formParams[elem.name] = setOrPush(formParams[elem.name], elem.value);
              }
          }
          return formParams;
      }




let a = 1;


}





function getAllUsers() {
    fetch(apiUserUrl)  
    .then(  
      function(response) {  
        if (response.status !== 200) {  
          alert('Error receiving data. Status Code: ' +  response.status);  
          return;  
        }
  
        response.json().then(function(data) {  
          // console.log(data);  
          let rows = '';
          data.forEach(user => {
            rows += '<tr>';
            rows += '<td>' + user.id + '</td>';
            rows += '<td>';
            user.roles.forEach(role => {
              rows += role.role + '<br>';
            });
            rows += '</td>';
            rows += '<td>' + user.login + '</td>';
            rows += '<td>' + user.password + '</td>';
            rows += '<td>' + user.email + '</td>';
            rows += '<td>';
            rows += '<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editUserModal" value="'
                 + user.id + '">Edit</button>' + '&nbsp;&nbsp';
            rows += '<a class="btn btn-danger btn-sm" href="' + apiUserUrl + '/' + user.id +'" role="button">Delete</a>';
            rows += '</td>';
            rows += '</tr>';

          });
          document.querySelector('tbody').innerHTML = rows;
        });  
      }  
    )  
    .catch(function(err) {  
      console.log('Fetch Error :-S', err);  
    });


}

function getModalCreateUser() {
  $('#createUserModal').modal('show');
  getAllRoles();
}

function getAllRoles() {
  fetch(apiRoleUrl)  
  .then(  
    function(response) {  
      if (response.status !== 200) {  
        console.log('Error receiving data. Status Code: ' +  response.status);  
        return;  
      }

      response.json().then(function(data) {  
        // console.log(data);
        let options = '';
        data.forEach(role => {
          let selected = role.role == 'ROLE_USER'? 'selected' : '';
          options += `<option value="${role.id}" ${selected}>${role.role}</option>`;
        });
        document.querySelector('#inputRole').innerHTML = options;
      });  
    }  
  )  
  .catch(function(err) {  
    console.log('Fetch Error :-S', err);  
  });
}
