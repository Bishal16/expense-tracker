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
    const userId = 7; // Replace with the logged-in user's ID

    useEffect(() => {
        fetchExpenses();
    }, []);

    const fetchExpenses = async () => {
        try {
            const response = await axios.get(
                `http://localhost:5001/api/expenses/list/${userId}`,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                }
            );
            setExpenses(response.data);
        } catch (error) {
            console.error("Failed to fetch expenses", error);
            alert("Failed to load expenses. Please try again.");
        }
    };

    const deleteExpense = async (expenseId) => {
        try {
            await axios.delete(`http://localhost:5001/api/expenses/${expenseId}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            alert("Expense deleted successfully");
            fetchExpenses(); // Refresh the list after deletion
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
