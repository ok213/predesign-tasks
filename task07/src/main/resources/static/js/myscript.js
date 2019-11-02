
const apiUserUrl = '/api/v1/user';
const apiRoleUrl = '/api/v1/role';
const btnSendUser = $('#btnSendUser');
$('#btnReadUsers').on('click', getAllUsers);
$('#btnMenuCreateUser').on('click', getModalCreateUser);

function getModalCreateUser() {
    $('#createUserModalTitle').text('Create new user');
    btnSendUser.text('Add new user');
    $('#formGroupId').addClass('d-none');
    btnSendUser.unbind('click');
    btnSendUser.on('click', userCreate);
    getAllRoles();
    $('#createUserModal').modal('show');
}

function userCreate(e){
    e.preventDefault();
    fetch(apiUserUrl, {
          method: "POST",
          body: JSON.stringify(getFormResults(e.target.form.elements)),
          // headers: { 'Content-type': 'application/x-www-form-urlencoded' }
          headers: {'Accept': 'application/json, text/plain',
                    'Content-type': 'application/json;charset=UTF-8'}
    })
    .then(function(response){
        if (response.status !== 200) {
            alert('Error receiving data. Status Code: ' +  response.status);
            return;
        }
        $('#inputId').val('');
        $('#inputEmail').val('');
        $('#inputLogin').val('');
        $('#inputPassword').val('');
        btnSendUser.unbind('click');
        $('#createUserModal').modal('hide');
        document.getElementById('btnReadUsers').click();

      })
    .catch(function(err) {
      console.log('Fetch Error :-S', err);
    });
}

function getModalUpdateUser(id) {
    fetch(apiUserUrl + '/' + id, {
        method: "GET"
    })
        .then(function(response){
            if (response.status !== 200) {
                alert('Error receiving data. Status Code: ' +  response.status);
                return;
            }
            response.json().then(function(data) {
                // console.log(data);
                $('#inputId').val(data.id);
                $('#inputEmail').val(data.email);
                $('#inputLogin').val(data.login);
                $('#inputPassword').val(data.password);
                $('#createUserModalTitle').text('Update user');
                btnSendUser.text('Update user');
                $('#formGroupId').removeClass('d-none');
                btnSendUser.unbind('click');
                btnSendUser.on('click', updateUser);
                getAllRoles();
                $('#createUserModal').modal('show');
            });
        })
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function updateUser(e) {
    e.preventDefault();
    let a = getFormResults(e.target.form.elements);
    let b =1;
    fetch(apiUserUrl, {
        method: "PUT",
        body: JSON.stringify(getFormResults(e.target.form.elements)),
        headers: {'Accept': 'application/json, text/plain',
            'Content-type': 'application/json;charset=UTF-8'}
    })
        .then(function(response){
            if (response.status !== 200) {
                alert('Error receiving data. Status Code: ' +  response.status);
                return;
            }
            $('#inputId').val('');
            $('#inputEmail').val('');
            $('#inputLogin').val('');
            $('#inputPassword').val('');
            btnSendUser.unbind('click');
            $('#createUserModal').modal('hide');
            $('#btnReadUsers').click();

        })
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function deleteUser(id) {
    fetch(apiUserUrl + '/' + id, {
        method: "DELETE"
    })
        .then(function(response){
            if (response.status !== 200) {
                alert('Error receiving data. Status Code: ' +  response.status);
                return;
            }
            $('#btnReadUsers').click();

        })
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function getAllUsers() {
    fetch(apiUserUrl,{
        method: "GET"
    })
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
                        rows += '<button type="button" class="btn btn-info btn-sm" onclick="getModalUpdateUser(' + user.id + ')">Edit</button>';
                        rows += '&nbsp;&nbsp';
                        rows += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteUser(' + user.id + ')">Delete</button>';
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

function getAllRoles() {
    fetch(apiRoleUrl,{
          method: "GET"
    })
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
          let selected = role.role === 'ROLE_USER'? 'selected' : '';
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

function getFormResults(formElements) {
    let formParams = {};
    for (let i = 0; i < formElements.length; i += 1) {
        elem = formElements[i];
        switch (elem.tagName) {
            case 'INPUT':
                if (elem.type === 'hidden') break;
                formParams[elem.name] = elem.value;
                break;
            case 'SELECT':
                formParams[elem.name] = [{'id': elem.value}];
        }
    }
    return formParams;
}

