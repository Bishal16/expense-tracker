import React, { useState, useEffect } from "react";
import {
    Container,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Button,
    Box,
} from "@mui/material";
import axios from "axios";

const Expense = () => {
    const [expenses, setExpenses] = useState([]);
    const [userId, setUserId] = useState(null); // State for user ID

    useEffect(() => {
        // Fetch user ID and then fetch expenses
        const fetchUserAndExpenses = async () => {
            try {
                const email = localStorage.getItem("email");
                const userResponse = await axios.get(
                    `http://localhost:5001/user/${email}`,
                    {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                    }
                );
                const userId = userResponse.data.id; // Assuming the response contains the user ID
                setUserId(userId); // Save userId to state
                fetchExpenses(userId); // Fetch expenses after getting userId
            } catch (error) {
                console.error("Failed to fetch user", error);
                alert("User not found. Please log in again.");
            }
        };

        fetchUserAndExpenses();
    }, []); // Runs once on component mount

    const fetchExpenses = async (userId) => {
        try {
            const response = await axios.get(
                `http://localhost:5001/api/expenses/list/${userId}`,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                }
            );
            setExpenses(response.data); // Update the expenses state
        } catch (error) {
            console.error("Failed to fetch expenses", error);
            alert("Failed to load expenses. Please try again.");
        }
    };

    const deleteExpense = async (expenseId) => {
        try {
            const confirmDelete = window.confirm("Are you sure you want to delete this expense?");
            if (confirmDelete) {
                await axios.delete(`http://localhost:5001/api/expenses/${expenseId}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                });
                alert("Expense deleted successfully");
                if (userId) {
                    fetchExpenses(userId);
                }
            } else {
                console.log("Expense deletion canceled.");
            }
        } catch (error) {
            console.error("Failed to delete expense", error);
            alert("Failed to delete expense. Please try again.");
        }
    };

    return (
        <Container maxWidth="lg">
            <Box my={4}>
                <Typography variant="h4" gutterBottom>
                    Expense Tracker
                </Typography>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Title</TableCell>
                                <TableCell>Amount</TableCell>
                                <TableCell>Category</TableCell>
                                <TableCell>Description</TableCell>
                                <TableCell>Actions</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {expenses.map((expense) => (
                                <TableRow key={expense.id}>
                                    <TableCell>{expense.title}</TableCell>
                                    <TableCell>{expense.amount}</TableCell>
                                    <TableCell>{expense.category}</TableCell>
                                    <TableCell>{expense.description}</TableCell>
                                    <TableCell>
                                        <Button
                                            variant="contained"
                                            color="secondary"
                                            onClick={() => deleteExpense(expense.id)}
                                        >
                                            Delete
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Container>
    );
};

export default Expense;
