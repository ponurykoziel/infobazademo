import React, { useEffect, useState } from 'react';
import { Table, Spin, Alert, Button, Modal } from 'antd';
import httpClient from './httpClient'; 
import UserDetailsSplash from './UserDetailsSplash';

const Roster = () => {

  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [unauthorizedMessage, setUnauthorizedMessage] = useState(null);
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [modalLoading, setModalLoading] = useState(false); 
  


  useEffect(() => {
    httpClient.get('/roster')
      .then(response => {
        setUsers(response.data);
      })
      .catch(error => {
        if (error.response && error.response.status === 401) {
          setUnauthorizedMessage("Unauthorized. You need to log in first.");
        }
        else {
          setError(error.message || "Error fetching users");
        }        
      })
      .finally(() => setLoading(false));
  }, []);


  //------------------------------------------------------------------------

  const fetchUserDetails = (userId) => {
    setModalLoading(true);
    httpClient.get(`/user/${userId}`)
      .then(response => setSelectedUser(response.data))
      .catch(error => {
        setError(error.message || "Error fetching user details");
        setSelectedUser(null);
      })
      .finally(() => setModalLoading(false));
  };

  const openUserDetails = (userId) => {    
    setIsModalOpen(true);
    fetchUserDetails(userId);
  };

  const closeUserDetails = () => {
    setIsModalOpen(false);
    setSelectedUser(null);
  };

  // Function to render the login message centrally (e.g., in a modal or centered div)
  const renderUnauthorizedMessage = () => {
    if (!unauthorizedMessage) return null;
    return (
      <div style={{
        position: 'fixed',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        backgroundColor: 'rgba(255, 233, 233, 0.9)',
        padding: '20px',
        borderRadius: '5px',
        boxShadow: '0 0 10px rgba(0, 0, 0, 0.3)',
        textAlign: 'center',
        zIndex: 1000
      }}>
        <p>{unauthorizedMessage}</p>
        <button onClick={() => {
          setUnauthorizedMessage(null);          
          window.location.href = '/login';
        }}>
          Try again
        </button>
      </div>
    );
  };


  //------------------------------------------------------------------------


  const columns = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    { title: 'Name', dataIndex: 'name', key: 'name' },
    { title: 'Username', dataIndex: 'username', key: 'username' },
    { title: 'Email', dataIndex: 'email', key: 'email' },
    { title: 'Phone', dataIndex: 'phone', key: 'phone' },  
    {
        title: 'Details', 
        key: 'details',
        render: (_, user) => (
            <Button type="link" onClick={()=> openUserDetails(user.id)}>
                details
            </Button>
        )
    }
  ];

  if (loading) return <Spin />;
  if (error) return <Alert type="error" message={error} />;

  return (    
    <>
    {renderUnauthorizedMessage()}
    
        <Table
            columns={columns}
            dataSource={users}
            rowKey="id"
        />
        <Modal
            title="User Details"
            open={isModalOpen}
            onOk={closeUserDetails}
            onCancel={closeUserDetails}
            footer={[
                <Button key="close" type="primary" onClick={closeUserDetails}>
                    Close
                </Button>
            ]}
        >
            { 
                modalLoading
                ? <Spin /> 
                : selectedUser 
                    ? <UserDetailsSplash selectedUser={selectedUser} />                    
                    : <p>User details aint available</p> 
            }
        </Modal>
    </>
  );
};

export default Roster;