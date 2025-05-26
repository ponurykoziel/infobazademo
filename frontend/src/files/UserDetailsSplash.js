import React, { useEffect, useState } from 'react';
import { Table, Spin, Alert, Button, Modal } from 'antd';
import httpClient from './httpClient'; 

const UserDetailsSplash = ({selectedUser}) => {
    return (
        <div>
                        <p><strong>ID:</strong> {selectedUser.id}</p>
                        <p><strong>Username:</strong> {selectedUser.username ?? ""}</p>
                        <p><strong>Name:</strong> {selectedUser.name ?? ""}</p>
                        <p><strong>Email:</strong> {selectedUser.email ?? ""}</p>
                        <p><strong>Phone:</strong> {selectedUser.phone  ?? ""}</p>
                        <p><strong>Website:</strong> {selectedUser.website ?? ""}</p>

                        <div>
                            <h4>Address</h4>
                            <p><strong>Street:</strong> {selectedUser.address?.street ?? ""}</p>
                            <p><strong>Suite:</strong> {selectedUser.address?.suite ?? ""}</p>
                            <p><strong>City:</strong> {selectedUser.address?.city ?? ""}</p>
                            <p><strong>Zipcode:</strong> {selectedUser.address?.zipcode ?? ""}</p>
                        </div>

                        <div>
                            <h4>Company</h4>
                            <p><strong>Company Name:</strong> {selectedUser.company?.name ?? ""}</p>
                            <p><strong>Catchphrase:</strong> {selectedUser.company?.catchPhrase ?? ""}</p>
                            <p><strong>BS:</strong> {selectedUser.company?.bs ?? ""}</p>
                        </div>
                        </div>
    );
};

export default UserDetailsSplash;
