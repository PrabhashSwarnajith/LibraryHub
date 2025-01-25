import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../service/AuthService'; // Make sure this import path is correct

const RegistrationPage = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    role: {
      member: false,
      librarian: false
    }
  });

  const [error, setError] = useState("");
  const navigate = useNavigate();
  const [success, setSuccess] = useState("");

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    const requestData = {
      name: `${formData.firstName} ${formData.lastName}`,
      email: formData.email,
      password: formData.password,
      role: ""
    };

    if (formData.role.member) {
      requestData.role = 'MEMBER'; 
    } else if (formData.role.librarian) {
      requestData.role = 'LIBRARIAN';
     }


    if (requestData.role.length === 0) {
      setError("At least one role must be selected.");
      return;
    }

    try {
      const response = await AuthService.signup(requestData); 

      console.log(response);
      setSuccess(response.message);
      setError("");

      navigate('/login');

      setFormData({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
        role: {
          member: false,
          librarian: false
        }
      });
    } catch (error) {
      console.error("Error registering user:", error);
      setError(error.message || "An error occurred.");
      setSuccess(""); 
    }
  };

  // Handle role change
  const handleRoleChange = (e) => {
    const { name, checked } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      role: { ...prevFormData.role, [name]: checked }
    }));
  };

  return (
    <div className='min-h-screen py-40 bg-red-200'> 
      <div className="container mx-auto"> 
        <div className='flex w-8/12 bg-white rounded-xl shadow-lg overflow-hidden mx-auto p-5 mb-5'>
          <div className='w-1/2'>
            <h2 className='text-2xl font-bold py-1'>Register</h2>
          </div>
          <div className='w-1/2 py-5 px-5'>
            <h2 className='text-3xl mb-4'>Register</h2>
            <p className='mb-4'>Create Your Account</p>
            <form onSubmit={handleSubmit}>
              <div className="grid grid-cols-2 gap-5">
                <input
                  type="text"
                  placeholder="First Name"
                  className="border-2 border-gray-600 rounded-lg py-1 px-1"
                  value={formData.firstName}
                  onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                />
                <input
                  type="text"
                  placeholder="Last Name"
                  className="border-2 border-gray-600 rounded-lg py-1 px-1"
                  value={formData.lastName}
                  onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
                />
              </div>

              <div className='mt-4'>
                <input
                  type="email"
                  placeholder="Email"
                  className="border-2 border-gray-600 rounded-lg py-1 px-1 w-full"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                />
              </div>

              <div className='mt-4'>
                <input
                  type="password"
                  placeholder="Password"
                  className="border-2 border-gray-600 rounded-lg py-1 px-1 w-full"
                  value={formData.password}
                  onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                />
              </div>

              <div className='mt-4'>
                <input
                  type="password"
                  placeholder="Confirm Password"
                  className="border-2 border-gray-600 rounded-lg py-1 px-1 w-full"
                  value={formData.confirmPassword}
                  onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
                />
              </div>

              <div className='mt-4 grid grid-cols-2 gap-4'>
                <label className="block">
                  <input
                    type="checkbox"
                    name="member"
                    checked={formData.role.member}
                    onChange={handleRoleChange}
                  />
                  Member
                </label>

                <label className="block">
                  <input
                    type="checkbox"
                    name="librarian"
                    checked={formData.role.librarian}
                    onChange={handleRoleChange}
                  />
                  Librarian
                </label>
              </div>

              <div className='mt-4'>
                {error && <p className="text-red-500">{error}</p>}
                {success && <p className="text-green-500">{success}</p>}
                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded-lg w-full">Register</button> 
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegistrationPage;
